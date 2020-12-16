package com.processor.core.typeform;

import com.processor.common.Util;
import com.processor.domain.TestConfiguration;
import org.apache.commons.httpclient.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.Map;

public class ATypeFormProvider {

    protected TestConfiguration testConfiguration;
    protected String formats[] = new String[]{"yyyy-MM-dd'T'HH:mm:ss'Z'", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"};
    protected static Logger logger = LoggerFactory.getLogger(ATypeFormProvider.class);

    protected void processAnswer(OutputStreamWriter writer, String[] fields, QuestionnaireAnswer answer) throws IOException {

        int j = 0;
        while (j < fields.length - 1) {
            String value = Util.getFieldValueAsString(answer.get(fields[j++]));
            if (value == null) {
                value = "";
            } else if (value.contains(",")) {
                value = '"' + value + '"';
            }
            writer.write(value + ",");
        }

        //last item
        String value = Util.getFieldValueAsString(answer.get(fields[j]));
        if (value == null) {
            value = "";
        } else if (value.contains(",")) {
            value = '"' + value + '"';
        }

        writer.write(value);

    }

    /**
     * save entries with output fields' names into a map
     *
     * @param answer
     * @param map
     * @throws Exception
     */
    protected void saveEntryToMap(QuestionnaireAnswer answer, Map<String, Object> map) throws Exception {

        String[] outputNames = testConfiguration.getOutputFields().split("\\s*,\\s*");
        String[] fields = testConfiguration.getFormFields().split("\\s*,\\s*");

        if (answer != null) {

            int j = 0;
            while (j < fields.length) {
                String value = Util.getFieldValueAsString(answer.get(fields[j]));
                if (value == null) {
                    value = "";
                }
                map.put(outputNames[j], value);
                j++;
            }
        }

    }

    protected JSONObject makeRequest(String url) throws IOException, JSONException {

        Header header1 = new Header("authorization", "bearer " + testConfiguration.getKey());
        Header header2 = new Header("Content-Type", "application/json;charset=UTF-8");

        return Util.makeGetRequest(url, header1, header2);
    }

    protected QuestionnaireAnswer processTypeformAnswers(JSONObject item) throws Exception {

        QuestionnaireAnswer output = new QuestionnaireAnswer();
        JSONArray answers = item.getJSONArray("answers");
        for (int j = 0; j < answers.length(); j++) {
            JSONObject answer = answers.getJSONObject(j);
            String fieldId = answer.getJSONObject("field").getString("id");
            String type = answer.getString("type");
            Object value = null;
            try {
                if (type.equalsIgnoreCase("text")) {
                    value = answer.getString("text");
                    output.put(fieldId, value);
                } else if (type.equalsIgnoreCase("date")) {
                    value = answer.getString("date");
                    output.put(fieldId, value);
                } else if (type.equalsIgnoreCase("number")) {
                    value = answer.getString("number");
                    output.put(fieldId, value);
                } else if (type.equalsIgnoreCase("choice")) {
                    value = answer.getJSONObject("choice").getString("label");
                    output.put(fieldId, value);
                }
            } catch (Exception ex) {
                logger.error("Error while processing the answer with id:" + fieldId, ex);
                continue;
            }
        }

        JSONObject hiddenFields = item.getJSONObject("hidden");
        Iterator<String> keys = hiddenFields.keys();
        while (keys.hasNext()) {
            String hKey = keys.next();
            output.put(hKey, hiddenFields.getString(hKey));
        }

        output.setDateCreated(Util.parseDate(item.getString("landed_at"), formats));
        output.put("landed_at", item.getString("landed_at"));
        output.setDateUpdated(Util.parseDate(item.getString("submitted_at"), formats));
        output.put("submitted_at", item.getString("submitted_at"));

        return output;
    }


}

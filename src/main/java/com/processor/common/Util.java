package com.processor.common;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    private static Logger logger = LoggerFactory.getLogger(Util.class);

    public static JSONObject makeGetRequest(String url, Header... headers) throws IOException, JSONException {
        HttpClient client = new HttpClient();
        String json = "";

        GetMethod get = new GetMethod(url);

        try {

            if (headers != null) {
                for (int i = 0; i < headers.length; i++) {
                    get.addRequestHeader(headers[i]);
                }
            }

            int status = client.executeMethod(get);

            InputStream inputStream = get.getResponseBodyAsStream();
            byte[] body = IOUtils.toByteArray(inputStream);
            json = new String(body, Charset.forName("UTF-8"));

            //json = get.getResponseBodyAsString();

            System.out.println(status + "\n" + json);
        } finally {
            get.releaseConnection();
        }

        return new JSONObject(json);
    }

    /**
     * @param dateString An input String, presumably from a user or a database table.
     * @param formats    An array of date formats that we have allowed for.
     * @return A Date (java.util.Date) reference. The reference will be null if
     * we could not match any of the known formats.
     */
    public static Date parseDate(String dateString, String[] formats) {

        Date date = null;

        for (int i = 0; i < formats.length; i++) {
            String format = formats[i];
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);

            try {
                // parse() will throw an exception if the given dateString
                // doesn't match
                // the current format
                date = dateFormat.parse(dateString);
                break;
            } catch (ParseException e) {
                logger.warn("error parsing date :"+dateString+" with format: "+format);
                // don't do anything. just let the loop continue.
                // we may miss on 99 format attempts, but match on one format,
                // but that's all we need.
            }
        }

        return date;

    }

    /***
     * Return a string representation of the value. null means ""
     *
     * @param value
     * @return
     */
    public static String getFieldValueAsString(Object value) {

        String stringValue = "";

        if (value != null && !value.equals("null") && value instanceof String) {
            stringValue = (String) value;
        } else if (value != null && !value.equals("null")) {
            stringValue = value.toString();
        }

        return stringValue;

    }

    public static int getIntegerFromObject(Object value) {

        if (value == null) {
            return 0;
        }

        try {
            int integer = Integer.parseInt(value.toString());
            return integer;
        } catch (NumberFormatException ex) {
            return 0;
        }
    }
}

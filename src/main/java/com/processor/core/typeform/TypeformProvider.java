package com.processor.core.typeform;


import com.processor.core.IQuestionnaireHelper;
import com.processor.domain.QuestionnaryDataPoint;
import com.processor.domain.RawAnswer;
import com.processor.domain.TestConfiguration;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * type forms implementation for pulling information
 */
@Component
public class TypeformProvider extends ATypeFormProvider implements IQuestionnaireHelper {

    public TypeformProvider() {
        super();
    }

    @Override
    public void setTestConfiguration(TestConfiguration testConfiguration){
        this.testConfiguration = testConfiguration;
    }

    @Override
    public RawAnswer saveJsonRawAnswer(JSONObject input) throws Exception {

        QuestionnaireAnswer answer = saveJsonAnswersToMap(input);
        RawAnswer rawRecord = new RawAnswer();
        saveQuestionnaireAnswerToQuestionnaireDataPoint(rawRecord, answer);
        return rawRecord;
    }

    private void saveQuestionnaireAnswerToQuestionnaireDataPoint(QuestionnaryDataPoint dataPoint, QuestionnaireAnswer answer) throws Exception {
        saveEntryToMap(answer, dataPoint.getMapProperties());
        dataPoint.setDbCreation(new Date());
        dataPoint.setDbUpdate(new Date());
        dataPoint.setFormId(testConfiguration.getFormCode());
    }

    /**
     * process a json typeform response
     *
     * @param input
     * @return
     * @throws Exception
     */
    private QuestionnaireAnswer saveJsonAnswersToMap(JSONObject input) throws Exception {

        QuestionnaireAnswer output;
        JSONArray answers = input.getJSONArray("answers");
        output = processTypeformAnswers(input);

        return output;
    }


}

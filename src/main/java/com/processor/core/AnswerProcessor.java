package com.processor.core;

import com.processor.aop.ErrorsHandler;
import com.processor.domain.RawAnswer;
import com.processor.domain.TestConfiguration;
import com.processor.repositories.RawAnswerRepository;
import com.processor.repositories.TestConfigurationRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnswerProcessor implements IAnswerProcessor {

    private static Logger logger = LoggerFactory.getLogger(AnswerProcessor.class);

    @Autowired
    private TestConfigurationRepository testConfigurationRepository;

    @Autowired
    private RawAnswerRepository rawAnswerRepository;

    @Autowired
    private IQuestionnaireHelper questionnaireHelper;

    @Override
    @ErrorsHandler
    public String processNewRecord(JSONObject recordToSave) throws Exception {

        if (recordToSave == null || !recordToSave.has("form_response") ) {
            logger.error("Invalid recordToSave. Doesn't have typeform structure");
            return null;
        }

        String formId = recordToSave.getJSONObject("form_response").getString("form_id");
        TestConfiguration testConfiguration = testConfigurationRepository.findOneByFormCode(formId);
        if (testConfiguration == null) {
            logger.error("No test configuration was found with id=" + formId);
            return null;
        }

        questionnaireHelper.setTestConfiguration(testConfiguration);
        RawAnswer dataPoint = questionnaireHelper.saveJsonRawAnswer(recordToSave.getJSONObject("form_response"));
        dataPoint = rawAnswerRepository.save(dataPoint);
        return dataPoint.getId();

    }
}

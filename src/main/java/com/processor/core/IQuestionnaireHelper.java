package com.processor.core;

import com.processor.domain.RawAnswer;
import com.processor.domain.TestConfiguration;
import org.json.JSONObject;

/**
 * interface for abstracting the general methods a test provider must provide.
 */
public interface IQuestionnaireHelper {

    /**
     * Takes a JSON object that represents a form answer and process it as a new raw answer
     * @param input
     * @return
     * @throws Exception
     */
    public RawAnswer saveJsonRawAnswer(JSONObject input) throws Exception;

    /**
     * test configuration definition
     *
     * @param testConfiguration
     */
    public void setTestConfiguration(TestConfiguration testConfiguration);

}

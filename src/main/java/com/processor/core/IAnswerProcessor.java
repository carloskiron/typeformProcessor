package com.processor.core;

import org.json.JSONObject;

public interface IAnswerProcessor {

    /**
     * process a new survey answer and returns its id
     * @param recordToSave
     * @return
     * @throws Exception
     */
    String processNewRecord(JSONObject recordToSave) throws Exception;

}

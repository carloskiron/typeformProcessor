package com.processor.controllers;

import javax.servlet.http.HttpServletRequest;

public interface IProcessorService {

    /**
     * saves a new incoming questionnaire answer. typeform webhooks
     *
     * @param request
     * @return
     * @throws Exception
     */
    boolean saveNewAnswer(HttpServletRequest request) throws Exception;

}

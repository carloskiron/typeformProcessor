package com.processor.controllers;

import com.processor.core.IAnswerProcessor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ProcessorController implements IProcessorService {

    private static Logger logger = LoggerFactory.getLogger(ProcessorController.class);

    @Autowired
    private IAnswerProcessor answerProcessor;

    /**
     * save the request in the formAnswer collection without any additional process
     *
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("${mapping.prefix}/newAnswer")
    public boolean saveNewAnswer(HttpServletRequest request) throws Exception {

        String body = request.getReader().lines()
                .reduce("", (accumulator, actual) -> accumulator + actual);
        JSONObject recordToSave = new JSONObject(body);
        return answerProcessor.processNewRecord(recordToSave) != null;
    }


    @RequestMapping("${mapping.prefix}/live")
    public boolean check() {
        return true;
    }
}

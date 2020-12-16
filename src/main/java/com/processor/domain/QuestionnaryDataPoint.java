package com.processor.domain;

public class QuestionnaryDataPoint extends BaseObject {

    private String formId;
    public QuestionnaryDataPoint() {
        super();
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }
}

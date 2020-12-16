package com.processor.core.typeform;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class QuestionnaireAnswer extends HashMap<String, Object> {

    private String CreatedBy;
    private Date DateCreated;
    private String UpdatedBy;
    private Date DateUpdated;

    public QuestionnaireAnswer(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public QuestionnaireAnswer(int initialCapacity) {
        super(initialCapacity);
    }

    public QuestionnaireAnswer() {
    }

    public QuestionnaireAnswer(Map<? extends String, ?> m) {
        super(m);
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public Date getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        DateCreated = dateCreated;
    }

    public String getUpdatedBy() {
        return UpdatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        UpdatedBy = updatedBy;
    }

    public Date getDateUpdated() {
        return DateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        DateUpdated = dateUpdated;
    }
}

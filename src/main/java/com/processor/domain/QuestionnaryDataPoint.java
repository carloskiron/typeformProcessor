package com.processor.domain;

public class QuestionnaryDataPoint extends BaseObject {

    private String entryId;
    private String formId; //the id of the form in the form system used by stellar
    private String name;
    private String applicantId;
    private int companyLocationCode;
    private int companyCode;
    private int jobPositionCode;

    public QuestionnaryDataPoint() {
        super();
    }

    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCompanyLocationCode() {
        return companyLocationCode;
    }

    public void setCompanyLocationCode(int companyLocationCode) {
        this.companyLocationCode = companyLocationCode;
    }

    public int getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(int companyCode) {
        this.companyCode = companyCode;
    }

    public int getJobPositionCode() {
        return jobPositionCode;
    }

    public void setJobPositionCode(int jobPositionCode) {
        this.jobPositionCode = jobPositionCode;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

}

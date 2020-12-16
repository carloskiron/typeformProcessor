package com.processor.domain;

/**
 * Class for making a configuration for a test to call through Stata for getting
 * a Score for every income entry
 *
 * @author carlosmontoya
 */
public class TestConfiguration {

    private String id;
    private String formCode;
    private String formFields;
    private String outputFields;
    private String formsProvider;
    private String key;

    public String getFormsProvider() {
        return formsProvider;
    }

    public void setFormsProvider(String formsProvider) {
        this.formsProvider = formsProvider;
    }

    public TestConfiguration() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFormCode() {
        return formCode;
    }

    public void setFormCode(String formCode) {
        this.formCode = formCode;
    }

    public String getFormFields() {
        return formFields;
    }

    public void setFormFields(String formFields) {
        this.formFields = formFields;
    }

    public String getOutputFields() {
        return outputFields;
    }

    public void setOutputFields(String outputFields) {
        this.outputFields = outputFields;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

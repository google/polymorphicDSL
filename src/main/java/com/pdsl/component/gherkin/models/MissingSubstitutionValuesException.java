package com.pdsl.component.gherkin.models;

public class MissingSubstitutionValuesException extends RuntimeException {
    public MissingSubstitutionValuesException(String message) {
        super(message);
    }
}

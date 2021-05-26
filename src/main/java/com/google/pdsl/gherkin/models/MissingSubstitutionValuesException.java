package com.google.pdsl.gherkin.models;

public class MissingSubstitutionValuesException extends RuntimeException {
    public MissingSubstitutionValuesException(String message) {
        super(message);
    }
}

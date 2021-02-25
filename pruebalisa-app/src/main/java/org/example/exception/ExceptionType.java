package org.example.exception;

public enum ExceptionType {

    UNKOWN_ERROR("00", "An unknown error occurred"),
    ERROR_PRICES("01", "Error retrieving prices");

    private final String code;
    private final String description;

    ExceptionType(String code, String description) {
        this.code =  code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
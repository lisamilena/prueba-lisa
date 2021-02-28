package org.example.exceptions;

import org.example.exception.ExceptionType;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private final String code;
    private final String description;

    public ApiException(String code, String message) {
        super(message);
        this.code = code;
        this.description = message;
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
        this.code = "";
        this.description = message.concat(" - " + cause.getMessage());
    }

    public ApiException(ExceptionType type) {
        super(type.getDescription());
        this.code = type.getCode();
        this.description = type.getDescription();
    }
}
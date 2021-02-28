package org.example.exception;

public abstract class AppException extends RuntimeException {

    private final String code;
    private final String description;
    private final ExceptionType type;

    private AppException() {
        super("App org.example.exception (Abstract)");
        code = null;
        description = null;
        type = null;
    }

    public AppException(String code, String message) {
        super(message);
        this.code = code;
        this.description = message;
        this.type = null;
    }

    public AppException(ExceptionType type) {
        super(type.getDescription());
        this.code = type.getCode();
        this.description = type.getDescription();
        this.type = type;
    }

    public ExceptionType getType() {
        return type;
    }
}
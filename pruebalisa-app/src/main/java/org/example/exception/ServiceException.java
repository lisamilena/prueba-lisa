package org.example.exception;

public class ServiceException extends AppException {

    public ServiceException(String code, String message) {
        super(code, message);
    }

    public ServiceException(ExceptionType type) {
        super(type);
    }

    public ServiceException(Throwable throwable) {
        super("", throwable.getMessage());
    }
}
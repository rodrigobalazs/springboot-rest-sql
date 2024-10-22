package com.rbalazs.storeapi.exception;

import org.springframework.http.HttpStatus;

/**
 * Custom Exception for the Store app, the error messages/status codes could be taken from {@link ErrorCode}
 *
 * @author Rodrigo Balazs
 */
public class CustomException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public CustomException(ErrorCode errorCode) {
        this.status = errorCode.getHttpStatus();
        this.message = errorCode.getMessage();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
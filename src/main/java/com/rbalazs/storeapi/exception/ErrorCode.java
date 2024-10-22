package com.rbalazs.storeapi.exception;

import org.springframework.http.HttpStatus;

/**
 * Enum which contains some commons exception status/messages.
 *
 * @author Rodrigo Balazs
 */
public enum ErrorCode {
    EMPTY_JSON_BODY(HttpStatus.BAD_REQUEST, "the JSON body cannot be empty"),
    EMPTY_FIELDS(HttpStatus.BAD_REQUEST, "Some mandatory fields are empty"),
    ENTITY_NON_UNIQUE(HttpStatus.BAD_REQUEST, "the entity already exist in the application"),
    ENTITY_NOT_FOUND(HttpStatus.BAD_REQUEST, "the entity was not found in the application");

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}

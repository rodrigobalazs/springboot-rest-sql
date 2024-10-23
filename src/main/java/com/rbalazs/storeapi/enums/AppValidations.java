package com.rbalazs.storeapi.enums;

import org.springframework.http.HttpStatus;

/**
 * Enum which contains some validation messages.
 *
 * @author Rodrigo Balazs
 */
public enum AppValidations {
    EMPTY_FIELDS(HttpStatus.BAD_REQUEST, "Some mandatory fields are empty"),
    ENTITY_NON_UNIQUE(HttpStatus.BAD_REQUEST, "the entity already exist in the application"),
    ENTITY_NOT_FOUND(HttpStatus.BAD_REQUEST, "the entity was not found in the application");

    private final HttpStatus httpStatus;
    private final String message;

    AppValidations(HttpStatus httpStatus, String message) {
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

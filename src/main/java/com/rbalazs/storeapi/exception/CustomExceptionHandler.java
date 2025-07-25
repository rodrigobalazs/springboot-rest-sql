package com.rbalazs.storeapi.exception;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Used to intercept {@link CustomException}
 *
 * @author Rodrigo Balazs
 */
@ControllerAdvice
public class CustomExceptionHandler {

    /**
     * Intercepts a given {@link CustomException} in order to return to the view an HTTP RESPONSE (JSON type) with the exception
     * message.
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }
}
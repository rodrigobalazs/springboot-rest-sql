package com.rbalazs.storeapi.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long entityId) {
        super(String.format("the entity with id:%s was not found", entityId));
    }
}
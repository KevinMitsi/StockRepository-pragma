package com.kevin.emazon.infraestructure.exceptions;

public class InvalidOrderingMethodException extends RuntimeException{
    public InvalidOrderingMethodException(String message) {
        super(message);
    }
}

package com.evaluation.movement.exception;

public class MovementException extends Exception {
    private String message;

    public MovementException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
}

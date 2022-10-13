package com.evaluation.movement.exception;

public class MovementNotFoundException extends Exception {
    private String message;

    public MovementNotFoundException(String cuenta) {
        super("Cuenta '" + cuenta + "' no existe");
        this.message = message;
    }
}

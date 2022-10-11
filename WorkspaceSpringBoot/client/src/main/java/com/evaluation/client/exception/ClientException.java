package com.evaluation.client.exception;

public class ClientException extends Exception {
    private String message;

    public ClientException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
}

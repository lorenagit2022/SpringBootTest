package com.evaluation.client.exception;

public class ClientNotFoundException extends Exception {
    private String clientName;

    public ClientNotFoundException(String clientName) {
        super("Cliente '" + clientName + "' no existe");
        this.clientName = clientName;
    }
}

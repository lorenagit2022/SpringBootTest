package com.evaluation.account.exception;

public class AccountNotFoundException extends Exception {
    private String clientName;

    public AccountNotFoundException(String clientName) {
        super("Cuenta '" + clientName + "' no existe");
        this.clientName = clientName;
    }
}

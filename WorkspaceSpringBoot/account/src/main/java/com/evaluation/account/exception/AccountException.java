package com.evaluation.account.exception;

public class AccountException extends Exception {
    private String message;

    public AccountException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

}

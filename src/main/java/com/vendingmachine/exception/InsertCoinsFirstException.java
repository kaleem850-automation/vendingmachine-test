package com.vendingmachine.exception;

public class InsertCoinsFirstException extends RuntimeException {
    private String message;

    public InsertCoinsFirstException(String string) {
        this.message = string;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

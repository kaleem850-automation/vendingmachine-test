package com.vendingmachine.exception;

public class InsufficientCoinsException extends RuntimeException {
    private String message;

    public InsufficientCoinsException(String string) {
        this.message = string;
    }

    @Override
    public String getMessage() {
        return message;
    }
}


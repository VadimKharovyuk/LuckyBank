package com.example.luckybank.Exception;

public class CardNotFoundException extends RuntimeException {

    public CardNotFoundException(String message) {
        super(message);
    }
}


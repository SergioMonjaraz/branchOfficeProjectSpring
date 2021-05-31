package com.example.demo.exceptions;

public class DataNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -3177583887304371115L;

    public DataNotFoundException(String message) {
        super(message);
    }

}

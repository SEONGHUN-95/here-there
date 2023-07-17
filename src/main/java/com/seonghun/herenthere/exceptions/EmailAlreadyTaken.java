package com.seonghun.herenthere.exceptions;

public class EmailAlreadyTaken extends RuntimeException {
    public EmailAlreadyTaken(String email) {
        super("The email already taken.");
    }
}

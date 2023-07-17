package com.seonghun.herenthere.security;

public enum Role {
    REGULAR_USER("REGULAR_USER"),
    ADMIN_USER("ADMIN_USER");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
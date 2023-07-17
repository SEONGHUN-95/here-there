package com.seonghun.herenthere.models;

public class UserId extends BaseEntityId {

    private UserId() {
        super();
    }

    public UserId(String value) {
        super(value);
    }

    public static UserId generate() {
        return new UserId(newTsid());
    }
}

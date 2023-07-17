package com.seonghun.herenthere.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class PostId extends BaseEntityId {
    private PostId() {
        super();
    }

    public PostId(String value) {
        super(value);
    }

    public static PostId generate() {
        return new PostId(newTsid());
    }

    public static PostId of(String id) {
        return new PostId(id);
    }
}

package com.seonghun.herenthere.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class CommentId extends BaseEntityId {

    private CommentId() {
        super();
    }

    public CommentId(String value) {
        super(value);
    }

    public static CommentId generate() {
        return new CommentId(newTsid());
    }

    public static CommentId of(String id) {
        return new CommentId(id);
    }
}

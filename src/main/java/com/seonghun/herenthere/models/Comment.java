package com.seonghun.herenthere.models;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {
    @EmbeddedId
    private CommentId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "post_id"))
    private PostId postId;

    private String content;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "user_id"))
    private UserId userId;

    private Comment() {
    }

    public Comment(CommentId id, PostId postId, String content, UserId userId) {
        this.id = id;
        this.postId = postId;
        this.content = content;
        this.userId = userId;
    }

    public CommentId id() {
        return id;
    }

    public PostId postId() {
        return postId;
    }

    public UserId userId() {
        return userId;
    }

    public String content() {
        return content;
    }

    public void update(String content) {
        this.content = content;
    }
}

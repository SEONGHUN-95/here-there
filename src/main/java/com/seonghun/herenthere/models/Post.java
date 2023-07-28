package com.seonghun.herenthere.models;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "posts")
public class Post {
    @EmbeddedId
    private PostId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "user_id"))
    private UserId userId;

    private String title;

    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private Post() {
    }

    public Post(PostId id, UserId userId, String title, String content) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public PostId id() {
        return id;
    }

    public UserId userId() {
        return userId;
    }

    public String title() {
        return title;
    }


    public String content() {
        return content;
    }

    public String createdAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일");
        return createdAt.format(formatter);
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}

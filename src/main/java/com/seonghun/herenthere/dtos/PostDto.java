package com.seonghun.herenthere.dtos;

import com.seonghun.herenthere.models.Post;

public record PostDto(
        String id,
        String userId,
        String title,
        String content,
        String createdAt
) {
    public PostDto(Post post) {
        this(post.id().toString(), post.userId().toString(), post.title(), post.content(), post.createdAt());
    }
}

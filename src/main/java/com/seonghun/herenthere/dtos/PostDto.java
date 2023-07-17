package com.seonghun.herenthere.dtos;

import com.seonghun.herenthere.models.Post;

public record PostDto(
        String id,
        String title,
        String content,
        String author
) {
    public PostDto(Post post) {
        this(post.id().toString(), post.author(),
                post.title(), post.content());
    }
}

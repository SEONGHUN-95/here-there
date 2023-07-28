package com.seonghun.herenthere.dtos;

import com.seonghun.herenthere.models.Comment;
import com.seonghun.herenthere.models.CommentId;

public record CommentDto(CommentId commentId, String content) {
    public CommentDto(Comment comment) {
        this(comment.id(), comment.content());
    }
}

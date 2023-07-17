package com.seonghun.herenthere.application.comment;

import com.seonghun.herenthere.exceptions.CommentNotFound;
import com.seonghun.herenthere.models.Comment;
import com.seonghun.herenthere.models.CommentId;
import com.seonghun.herenthere.models.PostId;
import com.seonghun.herenthere.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCommentService {
    private final CommentRepository commentRepository;

    public void deleteComment(String id, String postId) {
        Comment comment = commentRepository.findByIdAndPostId(CommentId.of(id), PostId.of(postId))
                .orElseThrow(CommentNotFound::new);

        commentRepository.delete(comment);
    }
}

package com.seonghun.herenthere.application.comment;

import com.seonghun.herenthere.dtos.CommentUpdateDto;
import com.seonghun.herenthere.exceptions.CommentNotFound;
import com.seonghun.herenthere.models.Comment;
import com.seonghun.herenthere.models.CommentId;
import com.seonghun.herenthere.models.PostId;
import com.seonghun.herenthere.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCommentService {
    private final CommentRepository commentRepository;

    public void updateComment(String id, String postId,
                              CommentUpdateDto commentUpdateDto) {
        Comment comment = commentRepository.findByIdAndPostId(CommentId.of(id), PostId.of(postId))
                .orElseThrow(CommentNotFound::new);

        comment.update(commentUpdateDto.content());

        commentRepository.save(comment);
    }
}

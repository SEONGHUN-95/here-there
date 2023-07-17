package com.seonghun.herenthere.application.comment;

import com.seonghun.herenthere.dtos.CommentCreateDto;
import com.seonghun.herenthere.models.Comment;
import com.seonghun.herenthere.models.CommentId;
import com.seonghun.herenthere.models.PostId;
import com.seonghun.herenthere.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCommentService {
    private final CommentRepository commentRepository;

    public void createComment(String postId, CommentCreateDto commentCreateDto) {
        Comment comment = new Comment(
                CommentId.generate(),
                PostId.of(postId),
                commentCreateDto.content(),
                commentCreateDto.author());
        commentRepository.save(comment);
    }
}

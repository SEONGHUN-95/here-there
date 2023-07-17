package com.seonghun.herenthere.application.comment;

import com.seonghun.herenthere.dtos.CommentDto;
import com.seonghun.herenthere.models.Comment;
import com.seonghun.herenthere.models.PostId;
import com.seonghun.herenthere.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCommentsService {
    private final CommentRepository commentRepository;

    public List<CommentDto> getCommentDtos(String postId) {
        List<Comment> comments =
                commentRepository.findAllByPostId(PostId.of(postId));

        return comments.stream().map(CommentDto::new).toList();
    }
}

package com.seonghun.herenthere.repositories;

import com.seonghun.herenthere.models.Comment;
import com.seonghun.herenthere.models.CommentId;
import com.seonghun.herenthere.models.PostId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, CommentId> {
    Optional<Comment> findByIdAndPostId(CommentId commentId, PostId postId);

    List<Comment> findAllByPostId(PostId postId);
}

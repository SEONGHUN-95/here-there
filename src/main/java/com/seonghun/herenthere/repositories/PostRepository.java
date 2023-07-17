package com.seonghun.herenthere.repositories;

import com.seonghun.herenthere.models.Post;
import com.seonghun.herenthere.models.PostId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, PostId> {
    List<Post> findAll();

    List<Post> findByTitleContaining(String keyword);
}

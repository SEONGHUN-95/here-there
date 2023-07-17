package com.seonghun.herenthere.repositories;

import com.seonghun.herenthere.models.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {

    Optional<Likes> findByPostIdAndUserId(String postId, String userId);

}

package com.seonghun.herenthere.application;

import com.seonghun.herenthere.exceptions.PostNotFound;
import com.seonghun.herenthere.models.Likes;
import com.seonghun.herenthere.repositories.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikePostService {
    private final LikesRepository likesRepository;

    public void likePost(String postId, String userId) {
        Likes likes = new Likes(postId, userId);
        likesRepository.save(likes);
    }

    public void unlikePost(String postId, String userId) {
        Likes likes = likesRepository.findByPostIdAndUserId(postId, userId).orElseThrow(PostNotFound::new);
        likesRepository.delete(likes);
    }

}

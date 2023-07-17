package com.seonghun.herenthere.application.post;

import com.seonghun.herenthere.exceptions.PostNotFound;
import com.seonghun.herenthere.models.Post;
import com.seonghun.herenthere.models.PostId;
import com.seonghun.herenthere.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletePostService {
    private final PostRepository postRepository;

    public void deletePost(String id) {
        Post post = postRepository.findById(PostId.of(id))
                .orElseThrow(PostNotFound::new);
        postRepository.delete(post);
    }
}

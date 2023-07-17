package com.seonghun.herenthere.application.post;

import com.seonghun.herenthere.dtos.PostCreateDto;
import com.seonghun.herenthere.models.Post;
import com.seonghun.herenthere.models.PostId;
import com.seonghun.herenthere.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePostService {
    private final PostRepository postRepository;

    public void createPost(PostCreateDto postCreateDto) {
        Post post = new Post(
                PostId.generate(),
                postCreateDto.author(),
                postCreateDto.title(),
                postCreateDto.content()
        );
        postRepository.save(post);
    }
}

package com.seonghun.herenthere.application.post;

import com.seonghun.herenthere.dtos.PostDto;
import com.seonghun.herenthere.models.Post;
import com.seonghun.herenthere.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPostsService {
    private final PostRepository postRepository;

    public List<PostDto> getPostDtos() {
        List<Post> posts = postRepository.findAll();

        return posts.stream().map(PostDto::new).toList();
    }
}

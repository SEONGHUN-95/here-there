package com.seonghun.herenthere.application;

import com.seonghun.herenthere.dtos.PostDto;
import com.seonghun.herenthere.models.Post;
import com.seonghun.herenthere.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final PostRepository postRepository;

    @Async
    public List<PostDto> searchAllWithKeyword(String kw) {
        List<Post> posts = postRepository.findByTitleContaining(kw);
        return posts.stream().map(PostDto::new).toList();
    }
}

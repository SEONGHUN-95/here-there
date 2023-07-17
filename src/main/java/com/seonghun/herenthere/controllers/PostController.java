package com.seonghun.herenthere.controllers;

import com.seonghun.herenthere.application.LikePostService;
import com.seonghun.herenthere.application.post.CreatePostService;
import com.seonghun.herenthere.application.post.DeletePostService;
import com.seonghun.herenthere.application.post.GetPostService;
import com.seonghun.herenthere.application.post.GetPostsService;
import com.seonghun.herenthere.application.post.UpdatePostService;
import com.seonghun.herenthere.dtos.PostCreateDto;
import com.seonghun.herenthere.dtos.PostDto;
import com.seonghun.herenthere.dtos.PostUpdateDto;
import com.seonghun.herenthere.exceptions.PostNotFound;
import com.seonghun.herenthere.models.UserId;
import com.seonghun.herenthere.security.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/post")
@CrossOrigin
@RequiredArgsConstructor
public class PostController {
    private final GetPostsService getPostsService;
    private final GetPostService getPostService;
    private final CreatePostService createPostService;
    private final UpdatePostService updatePostService;
    private final DeletePostService deletePostService;
    private final LikePostService likePostService;

    @GetMapping
    public List<PostDto> getPosts() {
        List<PostDto> postDtoList = getPostsService.getPostDtos();

        return postDtoList;
    }

    @GetMapping("/{id}")
    public PostDto getPost(@PathVariable String id) {
        PostDto postDto = getPostService.getPostDto(id);
        return postDto;
    }

    @PostMapping("/{id}/like")
    @ResponseStatus(HttpStatus.CREATED)
    public void likePost(@PathVariable String id,
                         Authentication authentication) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        UserId userId = new UserId(authUser.id());
        likePostService.likePost(id, userId.toString());
    }

    @DeleteMapping("/{id}/like")
    @ResponseStatus(HttpStatus.CREATED)
    public void unlikePost(@PathVariable String id,
                           Authentication authentication) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        UserId userId = new UserId(authUser.id());
        likePostService.unlikePost(id, userId.toString());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody PostCreateDto postCreateDto) {
        createPostService.createPost(postCreateDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePost(@PathVariable String id,
                           @RequestBody PostUpdateDto postUpdateDto) {
        updatePostService.updatePost(id, postUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable String id) {
        deletePostService.deletePost(id);
    }

    @ExceptionHandler(PostNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String postNotFound() {
        return "게시물을 찾을 수 없습니다.";
    }
}

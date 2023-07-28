package com.seonghun.herenthere.controllers;

import com.seonghun.herenthere.application.comment.CreateCommentService;
import com.seonghun.herenthere.application.comment.DeleteCommentService;
import com.seonghun.herenthere.application.comment.GetCommentsService;
import com.seonghun.herenthere.application.comment.UpdateCommentService;
import com.seonghun.herenthere.dtos.CommentCreateDto;
import com.seonghun.herenthere.dtos.CommentDto;
import com.seonghun.herenthere.dtos.CommentUpdateDto;
import com.seonghun.herenthere.exceptions.CommentNotFound;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin
@RequiredArgsConstructor
public class CommentController {
    private final GetCommentsService getCommentsService;
    private final CreateCommentService createCommentService;
    private final UpdateCommentService updateCommentService;
    private final DeleteCommentService deleteCommentService;

    @GetMapping
    public List<CommentDto> getComments(@RequestParam String postId) {
        List<CommentDto> commentDtos =
                getCommentsService.getCommentDtos(postId);

        return commentDtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestParam String postId,
                       Authentication authentication,
                       @RequestBody CommentCreateDto commentCreateDto) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        createCommentService.createComment(postId, authUser.id(), commentCreateDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
            @PathVariable String id,
            @RequestParam String postId,
            @RequestBody CommentUpdateDto commentUpdateDto
    ) {
        updateCommentService.updateComment(id, postId, commentUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id,
                       @RequestParam String postId) {
        deleteCommentService.deleteComment(id, postId);
    }

    @ExceptionHandler(CommentNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String postNotFound() {
        return "댓글을 찾을 수 없습니다.";
    }
}

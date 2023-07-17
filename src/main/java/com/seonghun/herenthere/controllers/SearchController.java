package com.seonghun.herenthere.controllers;

import com.seonghun.herenthere.application.SearchService;
import com.seonghun.herenthere.dtos.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    public List<PostDto> search(@RequestParam String keyword) {
        List<PostDto> postDtos = searchService.searchAllWithKeyword(keyword);
        return postDtos;
    }
}

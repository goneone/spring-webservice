package com.kuyt1819.webservice.web;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kuyt1819.webservice.dto.posts.PostsSaveRequestDto;
import com.kuyt1819.webservice.service.PostsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
	
	private final PostsService postsService;
	
	@PutMapping("/api/v1/posts")
	public Long save(@RequestBody PostsSaveRequestDto requestDto) {
		return postsService.save(requestDto);
	}
	
	
}

package com.kuyt1819.webservice.web;

import com.kuyt1819.webservice.service.PostsService;
import com.kuyt1819.webservice.web.dto.PostsSaveRequestDto;
import com.kuyt1819.webservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

	private final PostsService postsService;
	
	@PostMapping("/api/v1/posts")
	public Long save(@RequestBody PostsSaveRequestDto requestDto) {
	    return postsService.save(requestDto);
	}

	//@RequestMapping(value = "/api/v1/posts/{id}", produces = "application/json",  method=RequestMethod.PUT)
	@PutMapping("/api/v1/posts/{id}")
	public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
		return postsService.update(id, requestDto);
	}

	@DeleteMapping("/api/v1/posts/{id}")
	public Long delete(@PathVariable Long id) {
		postsService.delete(id);
		return id;
	}
}

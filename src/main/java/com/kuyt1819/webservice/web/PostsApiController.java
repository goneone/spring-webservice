package com.kuyt1819.webservice.web;

import com.kuyt1819.webservice.service.PostsService;
import com.kuyt1819.webservice.web.dto.PostsSaveRequestDto;
import com.kuyt1819.webservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

	private final PostsService postsService;
	
	@PostMapping("/api/v1/posts")
	public Long save(@RequestBody PostsSaveRequestDto requestDto) {
	    return postsService.save(requestDto);
	}

    //@PutMapping("/api/v1/posts/{id}")
    @RequestMapping(value = "/api/v1/posts/{id}", produces = "application/json",  method=RequestMethod.PUT)
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

}

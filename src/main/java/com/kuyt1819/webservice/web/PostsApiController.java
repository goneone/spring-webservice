package com.kuyt1819.webservice.web;

import com.kuyt1819.webservice.service.PostsService;
import com.kuyt1819.webservice.web.dto.PostsListResponseDto;
import com.kuyt1819.webservice.web.dto.PostsResponseDto;
import com.kuyt1819.webservice.web.dto.PostsSaveRequestDto;
import com.kuyt1819.webservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

	private final PostsService postsService;

	//글쓰기
	@PostMapping("/api/v1/posts")
	public Long save(@RequestBody PostsSaveRequestDto requestDto) {
	    return postsService.save(requestDto);
	}

	//글수정
	//@RequestMapping(value = "/api/v1/posts/{id}", produces = "application/json",  method=RequestMethod.PUT)
	@PutMapping("/api/v1/posts/{id}")
	public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
		return postsService.update(id, requestDto);
	}
    //why???
	//글삭제
	@DeleteMapping("/api/v1/posts/{id}")
	public Long delete(@PathVariable Long id) {
		postsService.delete(id);
		return id;
	}

	////글 하나 가져오기
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    ////전체 다가져오기기
   @GetMapping("/api/v1/posts/list")
    public List<PostsListResponseDto> findAll() {
        return postsService.findAllDesc();
    }
}

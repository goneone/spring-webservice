package com.kuyt1819.webservice.service;

import com.kuyt1819.webservice.domain.posts.Posts;
import com.kuyt1819.webservice.domain.posts.PostsRepository;
import com.kuyt1819.webservice.web.dto.PostsListResponseDto;
import com.kuyt1819.webservice.web.dto.PostsSaveRequestDto;
import com.kuyt1819.webservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto dto){
        return postsRepository.save(dto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" +id ));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }
}
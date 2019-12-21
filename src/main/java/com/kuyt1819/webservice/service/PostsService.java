package com.kuyt1819.webservice.service;

import com.kuyt1819.webservice.domain.posts.Posts;
import com.kuyt1819.webservice.domain.posts.PostsRepository;
import com.kuyt1819.webservice.web.dto.PostsListResponseDto;
import com.kuyt1819.webservice.web.dto.PostsResponseDto;
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

    @Transactional(readOnly = true) //readonly = true면 트랜잭션 범위는 유지하되, 조회기능만 남겨두어 조회 속도가 개선된다 -> 등록, 수정, 삭제 기능이 없는 서비스 메소드에서는 사용 추천
    public List<PostsListResponseDto> findAllDesc() {
        //postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto 변환 -> List로 반환하는 메소드
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

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

}
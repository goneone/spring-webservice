package com.kuyt1819.webservice.domain.posts;

import java.util.stream.Stream;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostsRepository extends JpaRepository<Posts, Long>{
	//단순히 인터페이스를 생성후, JpaRepository<Entity클래스, PK타입>를 상속하면 기본적인 CRUD 메소드가 자동생성 됨
	@Query("SELECT p " +
			"FROM Posts p " +
			"ORDER BY p.id DESC")
	Stream<Posts> findAllDesc();
}


package com.kuyt1819.webservice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long>{
	//�ܼ��� �������̽��� ������, JpaRepository<EntityŬ����, PKŸ��>�� ����ϸ� �⺻���� CRUD �޼ҵ尡 �ڵ����� ��
}

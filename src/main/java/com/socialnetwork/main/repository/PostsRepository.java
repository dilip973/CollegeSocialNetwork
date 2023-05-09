package com.socialnetwork.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialnetwork.main.model.Posts;


public interface PostsRepository extends JpaRepository<Posts, Long> {
}





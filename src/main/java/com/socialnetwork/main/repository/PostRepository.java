package com.socialnetwork.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialnetwork.main.model.Post;


public interface PostRepository extends JpaRepository<Post, Long> {
}





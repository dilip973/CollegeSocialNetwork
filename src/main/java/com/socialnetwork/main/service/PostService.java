package com.socialnetwork.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialnetwork.main.model.Post;
import com.socialnetwork.main.repository.PostRepository;


@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }
}

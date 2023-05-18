package com.socialnetwork.main.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.socialnetwork.main.dto.PostDTO;
import com.socialnetwork.main.model.Post;
import com.socialnetwork.main.repository.PostRepository;
import com.socialnetwork.main.repository.UserRepository;



@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/create")
    public ResponseEntity<String> createPostWithImageAndContent(@RequestParam("image") MultipartFile image,
                                                                @RequestParam("content") String content,
                                                                @RequestParam("title") String title)
                                                                {
    	// Authentication authentication)
       // Check if user is authenticated
//        if (authentication == null || !authentication.isAuthenticated()) {
//            return new ResponseEntity<>("User not authenticated.", HttpStatus.UNAUTHORIZED);
//        }

       // Get the authenticated user
//        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
//       String userName = userPrincipal.getName();

        try {
            // Validate the input
            if (image.isEmpty() || StringUtils.isEmpty(content)) {
                return new ResponseEntity<>("Image and content are required.", HttpStatus.BAD_REQUEST);
            }

            // Get the image data
            byte[] imageData = image.getBytes();

            // Create a new Post object
            Post post = new Post();
            post.setTitle(title);
            post.setContent(content);
            post.setImageData(imageData);
            post.setPostedDate(new Date());
           // post.setUserName(userName); // Set the username of the authenticated user

            
//            User user = userRepository.findByUsername(userName);
//            if (user == null) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
//            }
//
//            // Set the user in the post
//            post.setUserName(userName);
            
            // Save the post
            Post createdPost = postRepository.save(post);

            // Return the ID of the created post
            return new ResponseEntity<>("Created post with ID: " + createdPost.getId() +
                    ", Posted at: " + createdPost.getPostedDate(), HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to create post: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable("id") Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            return new ResponseEntity<>(post, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePost(@PathVariable("id") Long id,
                                             @RequestBody Post updatedPost) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            post.setTitle(updatedPost.getTitle());
            post.setContent(updatedPost.getContent());

            postRepository.save(post);
            return new ResponseEntity<>("Post updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Post not found.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            postRepository.delete(post);
            return new ResponseEntity<>("Post deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Post not found.", HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostDTO> postDTOs = new ArrayList<>();

        if (!posts.isEmpty()) {
            for (Post post : posts) {
                byte[] imageData = post.getImageData();
                String base64Image = Base64.getEncoder().encodeToString(imageData);

                PostDTO postDTO = new PostDTO();
                postDTO.setId(post.getId());
                postDTO.setTitle(post.getTitle());
                postDTO.setContent(post.getContent());
                postDTO.setImageData(base64Image);

                postDTOs.add(postDTO);
            }
            return new ResponseEntity<>(postDTOs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}



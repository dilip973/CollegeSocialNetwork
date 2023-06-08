package com.socialnetwork.main.testcase;

//public class PostControllerTest {
//
//}
import com.socialnetwork.main.controller.PostController;
import com.socialnetwork.main.dto.PostDTO;
import com.socialnetwork.main.model.Post;
import com.socialnetwork.main.repository.PostRepository;
import com.socialnetwork.main.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostControllerTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PostController postController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPostWithImageAndContent_withValidData_shouldReturnPostCreatedSuccessfully() throws IOException {
        // Arrange
        String title = "Test Post";
        String content = "This is a test post";
        byte[] imageData = "Test image data".getBytes();
        MultipartFile image = new MockMultipartFile("image", "test.jpg", "image/jpeg", imageData);

        Post post = new Post();
        post.setId(1L);
        post.setTitle(title);
        post.setContent(content);
        post.setImageData(imageData);
        post.setPostedDate(new Date());

        when(postRepository.save(any(Post.class))).thenReturn(post);

        // Act
        ResponseEntity<String> response = postController.createPostWithImageAndContent(image, content, title);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Created post with ID: 1, Posted at: " + post.getPostedDate(), response.getBody());
        verify(postRepository, times(1)).save(any(Post.class));
    }

    @Test
    void createPostWithImageAndContent_withEmptyImage_shouldReturnBadRequest() throws IOException {
        // Arrange
        String title = "Test Post";
        String content = "This is a test post";
        MultipartFile image = new MockMultipartFile("image", new byte[0]);

        // Act
        ResponseEntity<String> response = postController.createPostWithImageAndContent(image, content, title);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Image and content are required.", response.getBody());
        verify(postRepository, never()).save(any(Post.class));
    }

    @Test
    void createPostWithImageAndContent_withEmptyContent_shouldReturnBadRequest() throws IOException {
        // Arrange
        String title = "Test Post";
        String content = "";
        byte[] imageData = "Test image data".getBytes();
        MultipartFile image = new MockMultipartFile("image", "test.jpg", "image/jpeg", imageData);

        // Act
        ResponseEntity<String> response = postController.createPostWithImageAndContent(image, content, title);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Image and content are required.", response.getBody());
        verify(postRepository, never()).save(any(Post.class));
    }

    @Test
    void getPost_withValidPostId_shouldReturnPost() {
        // Arrange
        long postId = 1L;
        Post post = new Post();
        post.setId(postId);
        post.setTitle("Test Post");
        post.setContent("This is a test post");

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        // Act
        ResponseEntity<Post> response = postController.getPost(postId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(post, response.getBody());
        verify(postRepository, times(1)).findById(postId);
    }

    @Test
    void getPost_withInvalidPostId_shouldReturnNotFound() {
        // Arrange
        long postId = 1L;
        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Post> response = postController.getPost(postId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(postRepository, times(1)).findById(postId);
    }

    @Test
    void updatePost_withValidPostIdAndData_shouldReturnPostUpdatedSuccessfully() {
        // Arrange
        long postId = 1L;
        Post updatedPost = new Post();
        updatedPost.setTitle("Updated Post");
        updatedPost.setContent("This is the updated post content");

        Post existingPost = new Post();
        existingPost.setId(postId);
        existingPost.setTitle("Test Post");
        existingPost.setContent("This is a test post");

        when(postRepository.findById(postId)).thenReturn(Optional.of(existingPost));
        when(postRepository.save(any(Post.class))).thenReturn(existingPost);

        // Act
        ResponseEntity<String> response = postController.updatePost(postId, updatedPost);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Post updated successfully.", response.getBody());
        assertEquals(updatedPost.getTitle(), existingPost.getTitle());
        assertEquals(updatedPost.getContent(), existingPost.getContent());
        verify(postRepository, times(1)).save(existingPost);
    }

    @Test
    void updatePost_withInvalidPostId_shouldReturnNotFound() {
        // Arrange
        long postId = 1L;
        Post updatedPost = new Post();
        updatedPost.setTitle("Updated Post");
        updatedPost.setContent("This is the updated post content");

        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<String> response = postController.updatePost(postId, updatedPost);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Post not found.", response.getBody());
        verify(postRepository, never()).save(any(Post.class));
    }

    @Test
    void deletePost_withValidPostId_shouldReturnPostDeletedSuccessfully() {
        // Arrange
        long postId = 1L;
        Post existingPost = new Post();
        existingPost.setId(postId);
        existingPost.setTitle("Test Post");
        existingPost.setContent("This is a test post");

        when(postRepository.findById(postId)).thenReturn(Optional.of(existingPost));

        // Act
        ResponseEntity<String> response = postController.deletePost(postId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Post deleted successfully.", response.getBody());
        verify(postRepository, times(1)).delete(existingPost);
    }

    @Test
    void deletePost_withInvalidPostId_shouldReturnNotFound() {
        // Arrange
        long postId = 1L;
        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<String> response = postController.deletePost(postId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Post not found.", response.getBody());
        verify(postRepository, never()).delete(any(Post.class));
    }

    @Test
    void getAllPosts_withExistingPosts_shouldReturnAllPosts() {
        // Arrange
        List<Post> posts = new ArrayList<>();
        Post post1 = new Post();
        post1.setId(1L);
        post1.setTitle("Post 1");
        post1.setContent("This is post 1");
        post1.setImageData("Image 1".getBytes());
        post1.setPostedDate(new Date());
        posts.add(post1);
        Post post2 = new Post();
        post2.setId(2L);
        post2.setTitle("Post 2");
        post2.setContent("This is post 2");
        post2.setImageData("Image 2".getBytes());
        post2.setPostedDate(new Date());
        posts.add(post2);

        when(postRepository.findAll()).thenReturn(posts);

        // Act
        ResponseEntity<List<PostDTO>> response = postController.getAllPosts();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(posts.size(), response.getBody().size());
        verify(postRepository, times(1)).findAll();
    }

    @Test
    void getAllPosts_withNoPosts_shouldReturnNotFound() {
        // Arrange
        List<Post> posts = new ArrayList<>();
        when(postRepository.findAll()).thenReturn(posts);

        // Act
        ResponseEntity<List<PostDTO>> response = postController.getAllPosts();

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(postRepository, times(1)).findAll();
    }
}

package com.socialnetwork.main.testcase;

//public class PlacementControllerTest {
//
//}
import com.socialnetwork.main.controller.PlacementController;
import com.socialnetwork.main.model.ApprovedRegistration;
import com.socialnetwork.main.model.Post;
import com.socialnetwork.main.model.Student;
import com.socialnetwork.main.repository.PostRepository;
import com.socialnetwork.main.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PlacementControllerTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private HttpSession session;

    @InjectMocks
    private PlacementController placementController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllStudents_withExistingStudents_shouldReturnAllStudents() {
        // Arrange
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "John Doe", "Computer Science", 0, 85.0));
        students.add(new Student(2, "Jane Smith", "Electrical Engineering", 1, 75.5));
        when(studentRepository.findAll()).thenReturn(students);

        // Act
        List<Student> result = placementController.getAllStudents();

        // Assert
        assertEquals(students, result);
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void addStudent_withValidStudentDetailsAndLoggedInUser_shouldReturnStudentAddedSuccessfully() {
        // Arrange
        PlacementController.StudentDetails studentDetails = new PlacementController.StudentDetails();
        studentDetails.setId(1);
        studentDetails.setName("John Doe");
        studentDetails.setDepartment("Computer Science");
        studentDetails.setBacklogs(0);
        studentDetails.setPercentage(85.0);
        when(session.getAttribute("loggedInUser")).thenReturn(new ApprovedRegistration());

        // Act
        ResponseEntity<String> response = placementController.addStudent(studentDetails, session);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Student added successfully!", response.getBody());
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void addStudent_withInvalidPercentageAndLoggedInUser_shouldReturnPermissionDenied() {
        // Arrange
        PlacementController.StudentDetails studentDetails = new PlacementController.StudentDetails();
        studentDetails.setId(1);
        studentDetails.setName("John Doe");
        studentDetails.setDepartment("Computer Science");
        studentDetails.setBacklogs(0);
        studentDetails.setPercentage(-1.0);
        when(session.getAttribute("loggedInUser")).thenReturn(new ApprovedRegistration());

        // Act
        ResponseEntity<String> response = placementController.addStudent(studentDetails, session);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("You do not have permission to add a student.", response.getBody());
        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    void addPost_withValidPostDetailsAndLoggedInUser_shouldReturnPostAddedSuccessfully() {
        // Arrange
        PlacementController.PostDetails postDetails = new PlacementController.PostDetails();
        postDetails.setId(1L);
        postDetails.setTitle("Test Post");
        postDetails.setContent("This is a test post.");
        when(session.getAttribute("loggedInUser")).thenReturn(new ApprovedRegistration("placement officer"));
        when(postRepository.save(any(Post.class))).thenReturn(new Post());

        // Act
        ResponseEntity<String> response = placementController.addPost(postDetails, session);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Post added successfully!", response.getBody());
        verify(postRepository, times(1)).save(any(Post.class));
    }

    // Write more test methods for the other endpoints...
//    @Test
//    void deletePost_withValidPostIdAndLoggedInUser_shouldReturnPostDeletedSuccessfully() {
//        // Arrange
//        long postId = 1L;
//        ApprovedRegistration user = new ApprovedRegistration("placement officer");
//        Post post = new Post();
//        post.setId(postId);
//        post.setTitle("Test Post");
//        post.setContent("This is a test post");
//        post.setUsername(user.getUsername());
//        post.setPostedDate(new Date());
//        when(session.getAttribute("loggedInUser")).thenReturn(user);
//        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
//
//        // Act
//        ResponseEntity<String> response = placementController.deletePost(postId, session);
//
//        // Assert
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Post deleted successfully!", response.getBody());
//        verify(postRepository, times(1)).delete(post);
//    }

    @Test
    void deletePost_withInvalidPostIdAndLoggedInUser_shouldReturnPostNotFound() {
        // Arrange
        long postId = 1L;
        ApprovedRegistration user = new ApprovedRegistration("placement officer");
        when(session.getAttribute("loggedInUser")).thenReturn(user);
        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<String> response = placementController.deletePost(postId, session);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Post not found.", response.getBody());
        verify(postRepository, never()).delete(any(Post.class));
    }

    @Test
    void deletePost_withInvalidLoggedInUser_shouldReturnPermissionDenied() {
        // Arrange
        long postId = 1L;
        ApprovedRegistration user = new ApprovedRegistration("student");
        when(session.getAttribute("loggedInUser")).thenReturn(user);

        // Act
        ResponseEntity<String> response = placementController.deletePost(postId, session);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("You do not have permission to delete a post.", response.getBody());
        verify(postRepository, never()).delete(any(Post.class));
    }

//    @Test
//    void updatePost_withValidPostIdPostDetailsAndLoggedInUser_shouldReturnPostUpdatedSuccessfully() {
//        // Arrange
//        long postId = 1L;
//        ApprovedRegistration user = new ApprovedRegistration("placement officer");
//        PlacementController.PostDetails postDetails = new PlacementController.PostDetails();
//        postDetails.setTitle("Updated Post");
//        postDetails.setContent("This is the updated post content");
//        Post existingPost = new Post();
//        existingPost.setId(postId);
//        existingPost.setTitle("Test Post");
//        existingPost.setContent("This is a test post");
//        existingPost.setUsername(user.getUsername());
//        existingPost.setPostedDate(new Date());
//        when(session.getAttribute("loggedInUser")).thenReturn(user);
//        when(postRepository.findById(postId)).thenReturn(Optional.of(existingPost));
//
//        // Act
//        ResponseEntity<String> response = placementController.updatePost(postId, postDetails, session);
//
//        // Assert
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Post updated successfully!", response.getBody());
//        assertEquals(postDetails.getTitle(), existingPost.getTitle());
//        assertEquals(postDetails.getContent(), existingPost.getContent());
//        verify(postRepository, times(1)).save(existingPost);
//    }

    @Test
    void updatePost_withInvalidPostIdAndLoggedInUser_shouldReturnPostNotFound() {
        // Arrange
        long postId = 1L;
        ApprovedRegistration user = new ApprovedRegistration("placement officer");
        PlacementController.PostDetails postDetails = new PlacementController.PostDetails();
        postDetails.setTitle("Updated Post");
        postDetails.setContent("This is the updated post content");
        when(session.getAttribute("loggedInUser")).thenReturn(user);
        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<String> response = placementController.updatePost(postId, postDetails, session);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Post not found.", response.getBody());
        verify(postRepository, never()).save(any(Post.class));
    }

    @Test
    void updatePost_withInvalidLoggedInUser_shouldReturnPermissionDenied() {
        // Arrange
        long postId = 1L;
        ApprovedRegistration user = new ApprovedRegistration("student");
        PlacementController.PostDetails postDetails = new PlacementController.PostDetails();
        postDetails.setTitle("Updated Post");
        postDetails.setContent("This is the updated post content");
        when(session.getAttribute("loggedInUser")).thenReturn(user);

        // Act
        ResponseEntity<String> response = placementController.updatePost(postId, postDetails, session);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("You do not have permission to update a post.", response.getBody());
        verify(postRepository, never()).save(any(Post.class));
    }

}

package com.socialnetwork.main.testcase;

//public class StudentControllerTest {
//
//}

import com.socialnetwork.main.controller.StudentController;
import com.socialnetwork.main.model.ApprovedNewsFeed;
import com.socialnetwork.main.model.ApprovedRegistration;
import com.socialnetwork.main.model.NewsFeed;
import com.socialnetwork.main.repository.ApprovedNewsFeedRepository;
import com.socialnetwork.main.repository.NewsFeedRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StudentControllerTest {

    @Mock
    private NewsFeedRepository newsFeedRepository;

    @Mock
    private ApprovedNewsFeedRepository approvedNewsFeedRepository;

    @Mock
    private HttpSession session;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addArticle_withValidSessionAndUserRole_shouldAddArticleAndReturnSuccessMessage() throws Exception {
        // Arrange
        ApprovedRegistration user = new ApprovedRegistration();
        user.setRole("student");
        when(session.getAttribute("loggedInUser")).thenReturn(user);
        MultipartFile image = new MockMultipartFile("image", "test.jpg", "image/jpeg", "test image".getBytes());
        String title = "Test Article";
        String content = "This is a test article.";

        // Act
        ResponseEntity<String> response = studentController.addArticle(image, title, content, session);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Article added successfully.", response.getBody());
        verify(newsFeedRepository, times(1)).save(any(NewsFeed.class));
    }

    @Test
    void addArticle_withInvalidSessionOrUserRole_shouldReturnBadRequest() throws Exception {
        // Arrange
        when(session.getAttribute("loggedInUser")).thenReturn(null);
        MultipartFile image = new MockMultipartFile("image", "test.jpg", "image/jpeg", "test image".getBytes());
        String title = "Test Article";
        String content = "This is a test article.";

        // Act
        ResponseEntity<String> response = studentController.addArticle(image, title, content, session);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid session or user role.", response.getBody());
        verify(newsFeedRepository, never()).save(any(NewsFeed.class));
    }

    @Test
    void updateArticle_withValidSessionAndUserRoleAndExistingArticleId_shouldUpdateArticleAndReturnSuccessMessage() throws Exception {
        // Arrange
        ApprovedRegistration user = new ApprovedRegistration();
        user.setRole("student");
        user.setId(1L);
        when(session.getAttribute("loggedInUser")).thenReturn(user);
        MultipartFile image = new MockMultipartFile("image", "test.jpg", "image/jpeg", "test image".getBytes());
        String title = "Updated Article";
        String content = "This is an updated article.";
        Long articleId = 1L;
        ApprovedNewsFeed existingArticle = new ApprovedNewsFeed();
        existingArticle.setId(articleId);
        existingArticle.setStudentId(user.getId());
        when(approvedNewsFeedRepository.findById(articleId)).thenReturn(Optional.of(existingArticle));

        // Act
        ResponseEntity<String> response = studentController.updateArticle(articleId, Optional.of(image), title, content, session);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Article updated successfully.", response.getBody());
        verify(approvedNewsFeedRepository, times(1)).save(any(ApprovedNewsFeed.class));
    }

    @Test
    void updateArticle_withInvalidSessionOrUserRole_shouldReturnBadRequest() throws Exception {
        // Arrange
        when(session.getAttribute("loggedInUser")).thenReturn(null);
        MultipartFile image = new MockMultipartFile("image", "test.jpg", "image/jpeg", "test image".getBytes());
        String title = "Updated Article";
        String content = "This is an updated article.";
        Long articleId = 1L;

        // Act
        ResponseEntity<String> response = studentController.updateArticle(articleId, Optional.of(image), title, content, session);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid session or user role.", response.getBody());
        verify(approvedNewsFeedRepository, never()).save(any(ApprovedNewsFeed.class));
    }

    @Test
    void updateArticle_withValidSessionAndUserRoleAndNonExistingArticleId_shouldReturnBadRequest() throws Exception {
        // Arrange
        ApprovedRegistration user = new ApprovedRegistration();
        user.setRole("student");
        when(session.getAttribute("loggedInUser")).thenReturn(user);
        MultipartFile image = new MockMultipartFile("image", "test.jpg", "image/jpeg", "test image".getBytes());
        String title = "Updated Article";
        String content = "This is an updated article.";
        Long nonExistingArticleId = 1L;
        when(approvedNewsFeedRepository.findById(nonExistingArticleId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<String> response = studentController.updateArticle(nonExistingArticleId, Optional.of(image), title, content, session);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Article not found.", response.getBody());
        verify(approvedNewsFeedRepository, never()).save(any(ApprovedNewsFeed.class));
    }

    @Test
    void updateArticle_withValidSessionAndUserRoleAndExistingArticleIdButNoPermission_shouldReturnBadRequest() throws Exception {
        // Arrange
        ApprovedRegistration user = new ApprovedRegistration();
        user.setRole("student");
        user.setId(1L);
        when(session.getAttribute("loggedInUser")).thenReturn(user);
        MultipartFile image = new MockMultipartFile("image", "test.jpg", "image/jpeg", "test image".getBytes());
        String title = "Updated Article";
        String content = "This is an updated article.";
        Long articleId = 1L;
        ApprovedNewsFeed existingArticle = new ApprovedNewsFeed();
        existingArticle.setId(articleId);
        existingArticle.setStudentId(2L); // Different student ID
        when(approvedNewsFeedRepository.findById(articleId)).thenReturn(Optional.of(existingArticle));

        // Act
        ResponseEntity<String> response = studentController.updateArticle(articleId, Optional.of(image), title, content, session);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("You don't have permission to update this article.", response.getBody());
        verify(approvedNewsFeedRepository, never()).save(any(ApprovedNewsFeed.class));
    }

    @Test
    void deleteArticle_withValidSessionAndUserRole_shouldDeleteArticleAndReturnSuccessMessage() {
        // Arrange
        ApprovedRegistration user = new ApprovedRegistration();
        user.setRole("student");
        when(session.getAttribute("loggedInUser")).thenReturn(user);
        Long articleId = 1L;

        // Act
        ResponseEntity<String> response = studentController.deleteArticle(articleId, session);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Article deleted successfully.", response.getBody());
        verify(approvedNewsFeedRepository, times(1)).deleteById(articleId);
    }

    @Test
    void deleteArticle_withInvalidSessionOrUserRole_shouldReturnBadRequest() {
        // Arrange
        when(session.getAttribute("loggedInUser")).thenReturn(null);
        Long articleId = 1L;

        // Act
        ResponseEntity<String> response = studentController.deleteArticle(articleId, session);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid session or user role.", response.getBody());
        verify(approvedNewsFeedRepository, never()).deleteById(articleId);
    }
}


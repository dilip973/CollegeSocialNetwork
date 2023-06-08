package com.socialnetwork.main.testcase;

//public class UserControllerTest {
//
//}
import com.socialnetwork.main.bto.Message;
import com.socialnetwork.main.controller.UserController;
import com.socialnetwork.main.model.*;
import com.socialnetwork.main.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private ApprovedRegistrationRepository approvedRegistrationRepository;
    @Mock
    private ApprovedNewsFeedRepository approvedNewsFeedRepository;
    @Mock
    private EventRepository eventRepository;
    @Mock
    private FacultyRepository facultyRepository;
    @Mock
    private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private HttpSession session;

    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController();
        userController.setStudentRepository(studentRepository);
        userController.setApprovedRegistrationRepository(approvedRegistrationRepository);
        userController.setApprovedNewsFeedRepository(approvedNewsFeedRepository);
        userController.setEventRepository(eventRepository);
        userController.setFacultyRepository(facultyRepository);
        userController.setPostRepository(postRepository);
        userController.setUserRepository(userRepository);
        userController.setPasswordEncoder(passwordEncoder);
    }

    @Test
    void getAllStudents_withLoggedInUser_shouldReturnStudents() {
        // Arrange
        when(session.getAttribute("loggedInUser")).thenReturn(new Object());
        List<Student> students = new ArrayList<>();
        students.add(new Student());
        students.add(new Student());
        when(studentRepository.findAll()).thenReturn(students);

        // Act
        ResponseEntity<List<Student>> response = userController.getAllStudents(session);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(students, response.getBody());
    }

    @Test
    void getAllStudents_withoutLoggedInUser_shouldReturnBadRequest() {
        // Arrange
        when(session.getAttribute("loggedInUser")).thenReturn(null);

        // Act
        ResponseEntity<List<Student>> response = userController.getAllStudents(session);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getAllRegistrations_withLoggedInUser_shouldReturnRegistrations() {
        // Arrange
        when(session.getAttribute("loggedInUser")).thenReturn(new Object());
        List<ApprovedRegistration> registrations = new ArrayList<>();
        registrations.add(new ApprovedRegistration());
        registrations.add(new ApprovedRegistration());
        when(approvedRegistrationRepository.findAll()).thenReturn(registrations);

        // Act
        ResponseEntity<List<ApprovedRegistration>> response = userController.getAllRegistrations(session);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(registrations, response.getBody());
    }

    @Test
    void getAllRegistrations_withoutLoggedInUser_shouldReturnBadRequest() {
        // Arrange
        when(session.getAttribute("loggedInUser")).thenReturn(null);

        // Act
        ResponseEntity<List<ApprovedRegistration>> response = userController.getAllRegistrations(session);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getAllNewsFeed_withLoggedInUser_shouldReturnNewsFeed() {
        // Arrange
        when(session.getAttribute("loggedInUser")).thenReturn(new Object());
        List<ApprovedNewsFeed> newsFeed = new ArrayList<>();
        newsFeed.add(new ApprovedNewsFeed());
        newsFeed.add(new ApprovedNewsFeed());
        when(approvedNewsFeedRepository.findAll()).thenReturn(newsFeed);

        // Act
        ResponseEntity<List<ApprovedNewsFeed>> response = userController.getAllNewsFeed(session);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newsFeed, response.getBody());
    }

    @Test
    void getAllNewsFeed_withoutLoggedInUser_shouldReturnBadRequest() {
        // Arrange
        when(session.getAttribute("loggedInUser")).thenReturn(null);

        // Act
        ResponseEntity<List<ApprovedNewsFeed>> response = userController.getAllNewsFeed(session);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getAllEvents_withLoggedInUser_shouldReturnEvents() {
        // Arrange
        when(session.getAttribute("loggedInUser")).thenReturn(new Object());
        List<Event> events = new ArrayList<>();
        events.add(new Event());
        events.add(new Event());
        when(eventRepository.findAll()).thenReturn(events);

        // Act
        ResponseEntity<List<Event>> response = userController.getAllEvents(session);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(events, response.getBody());
    }

    @Test
    void getAllEvents_withoutLoggedInUser_shouldReturnBadRequest() {
        // Arrange
        when(session.getAttribute("loggedInUser")).thenReturn(null);

        // Act
        ResponseEntity<List<Event>> response = userController.getAllEvents(session);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getAllFaculties_withLoggedInUser_shouldReturnFaculties() {
        // Arrange
        when(session.getAttribute("loggedInUser")).thenReturn(new Object());
        List<Faculty> faculties = new ArrayList<>();
        faculties.add(new Faculty());
        faculties.add(new Faculty());
        when(facultyRepository.findAll()).thenReturn(faculties);

        // Act
        ResponseEntity<List<Faculty>> response = userController.getAllFaculties(session);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(faculties, response.getBody());
    }

    @Test
    void getAllFaculties_withoutLoggedInUser_shouldReturnBadRequest() {
        // Arrange
        when(session.getAttribute("loggedInUser")).thenReturn(null);

        // Act
        ResponseEntity<List<Faculty>> response = userController.getAllFaculties(session);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getAllPosts_withLoggedInUser_shouldReturnPosts() {
        // Arrange
        when(session.getAttribute("loggedInUser")).thenReturn(new Object());
        List<Post> posts = new ArrayList<>();
        posts.add(new Post());
        posts.add(new Post());
        when(postRepository.findAll()).thenReturn(posts);

        // Act
        ResponseEntity<List<Post>> response = userController.getAllPosts(session);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(posts, response.getBody());
    }

    @Test
    void getAllPosts_withoutLoggedInUser_shouldReturnBadRequest() {
        // Arrange
        when(session.getAttribute("loggedInUser")).thenReturn(null);

        // Act
        ResponseEntity<List<Post>> response = userController.getAllPosts(session);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void login_withValidCredentials_shouldReturnUser() {
        // Arrange
        User user = new User();
        user.setEmailId("test@example.com");
        user.setPassword("password");
        User uDB = new User();
        uDB.setEmailId("test@example.com");
        uDB.setPassword(passwordEncoder.encode("password"));
        when(userRepository.findByEmailId(user.getEmailId())).thenReturn(uDB);
        when(passwordEncoder.matches(user.getPassword(), uDB.getPassword())).thenReturn(true);

        // Act
        ResponseEntity<Object> response = userController.login(user, session, null);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(uDB, response.getBody());
        verify(session).setAttribute("loggedInUser", uDB);
    }

    @Test
    void login_withInvalidCredentials_shouldReturnBadRequest() {
        // Arrange
        User user = new User();
        user.setEmailId("test@example.com");
        user.setPassword("password");
        when(userRepository.findByEmailId(user.getEmailId())).thenReturn(null);

        // Act
        ResponseEntity<Object> response = userController.login(user, session, null);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void login_withIncorrectPassword_shouldReturnBadRequest() {
        // Arrange
        User user = new User();
        user.setEmailId("test@example.com");
        user.setPassword("password");
        User uDB = new User();
        uDB.setEmailId("test@example.com");
        uDB.setPassword(passwordEncoder.encode("wrongpassword"));
        when(userRepository.findByEmailId(user.getEmailId())).thenReturn(uDB);
        when(passwordEncoder.matches(user.getPassword(), uDB.getPassword())).thenReturn(false);

        // Act
        ResponseEntity<Object> response = userController.login(user, session, null);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void login_withPrincipal_shouldReturnUser() {
        // Arrange
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("test@example.com");
        User user = new User();
        user.setEmailId("test@example.com");
        when(userRepository.getUserByEmailId(principal.getName())).thenReturn(user);

        // Act
        User result = userController.login(principal);

        // Assert
        assertEquals(user, result);
    }
}

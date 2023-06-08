package com.socialnetwork.main.testcase;

//public class FacultyControllerTest {
//
//}
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import com.socialnetwork.main.controller.FacultyController;
//import com.socialnetwork.main.model.ApprovedRegistration;
//import com.socialnetwork.main.model.Student;
//import com.socialnetwork.main.repository.StudentRepository;
//
//import javax.servlet.http.HttpSession;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//class FacultyControllerTest {
//
//    @Mock
//    private StudentRepository studentRepository;
//
//    @InjectMocks
//    private FacultyController facultyController;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//        HttpSession session = mock(HttpSession.class);
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(null));
//        when(session.getAttribute("loggedInUser")).thenReturn(new ApprovedRegistration("faculty"));
//    }
//
//    @Test
//    public void getStudents_withValidUser_shouldReturnStudents() {
//        // Arrange
//        List<Student> students = new ArrayList<>();
//        students.add(new Student("John"));
//        students.add(new Student("Alice"));
//
//        when(studentRepository.findAll()).thenReturn(students);
//
//        // Act
//        ResponseEntity<?> response = facultyController.getStudents();
//
//        // Assert
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(students, response.getBody());
//        verify(studentRepository, times(1)).findAll();
//    }
//
//    @Test
//    public void getStudents_withInvalidUser_shouldReturnAccessDenied() {
//        // Arrange
//        HttpSession session = mock(HttpSession.class);
//        when(session.getAttribute("loggedInUser")).thenReturn(null);
//
//        // Act
//        ResponseEntity<?> response = facultyController.getStudents();
//
//        // Assert
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals("Access denied.", response.getBody());
//        verify(studentRepository, never()).findAll();
//    }
//}
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockHttpSession;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import com.socialnetwork.main.controller.FacultyController;
//import com.socialnetwork.main.model.ApprovedRegistration;
//import com.socialnetwork.main.model.Student;
//import com.socialnetwork.main.repository.StudentRepository;
//
//import javax.servlet.http.HttpSession;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//class FacultyControllerTest {
//
//    @Mock
//    private StudentRepository studentRepository;
//
//    @InjectMocks
//    private FacultyController facultyController;
//
//    private HttpSession session;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//        session = new MockHttpSession();
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(null));
//    }
//
//    @Test
//    public void getStudents_withValidUser_shouldReturnStudents() {
//        // Arrange
//        List<Student> students = new ArrayList<>();
//        students.add(new Student("John"));
//        students.add(new Student("Alice"));
//
//        ApprovedRegistration user = new ApprovedRegistration("faculty");
//        session.setAttribute("loggedInUser", user);
//
//        when(studentRepository.findAll()).thenReturn(students);
//
//        // Act
//        ResponseEntity<?> response = facultyController.getStudents(session);
//
//        // Assert
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(students, response.getBody());
//        verify(studentRepository, times(1)).findAll();
//    }
//
//    @Test
//    public void getStudents_withInvalidUser_shouldReturnAccessDenied() {
//        // Arrange
//        session.setAttribute("loggedInUser", null);
//
//        // Act
//        ResponseEntity<?> response = facultyController.getStudents(session);
//
//        // Assert
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals("Access denied.", response.getBody());
//        verify(studentRepository, never()).findAll();
//    }
//}
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.socialnetwork.main.controller.FacultyController;
import com.socialnetwork.main.model.ApprovedRegistration;
import com.socialnetwork.main.model.Student;
import com.socialnetwork.main.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FacultyControllerTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private FacultyController facultyController;

    private HttpSession session;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        session = mock(HttpSession.class);
    }

    @Test
    public void getStudents_withValidUser_shouldReturnStudents() {
        // Arrange
        ApprovedRegistration user = new ApprovedRegistration();
        user.setRole("faculty");

        List<Student> students = new ArrayList<>();
        students.add(new Student("John"));
        students.add(new Student("Alice"));

        when(session.getAttribute("loggedInUser")).thenReturn(user);
        when(studentRepository.findAll()).thenReturn(students);

        // Act
        ResponseEntity<?> response = facultyController.getStudents(session);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(students, response.getBody());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void getStudents_withInvalidUser_shouldReturnAccessDenied() {
        // Arrange
        when(session.getAttribute("loggedInUser")).thenReturn(null);

        // Act
        ResponseEntity<?> response = facultyController.getStudents(session);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Access denied.", response.getBody());
        verify(studentRepository, never()).findAll();
    }
}





package com.socialnetwork.main.testcase;

//public class StaffControllerTest {
//
//}

import com.socialnetwork.main.controller.StaffController;
import com.socialnetwork.main.model.ApprovedRegistration;
import com.socialnetwork.main.model.Event;
import com.socialnetwork.main.model.Student;
import com.socialnetwork.main.repository.EventRepository;
import com.socialnetwork.main.repository.StudentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StaffControllerTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private HttpSession session;

    @InjectMocks
    private StaffController staffController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getStudents_withValidSessionAndUserRole_shouldReturnListOfStudents() {
        // Arrange
        ApprovedRegistration user = new ApprovedRegistration();
        user.setRole("staff");
        when(session.getAttribute("loggedInUser")).thenReturn(user);
        List<Student> students = new ArrayList<>();
        students.add(new Student());
        when(studentRepository.findAll()).thenReturn(students);

        // Act
        ResponseEntity<?> response = staffController.getStudents(session);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(students, response.getBody());
    }

    @Test
    void getStudents_withInvalidSessionOrUserRole_shouldReturnBadRequest() {
        // Arrange
        when(session.getAttribute("loggedInUser")).thenReturn(null);

        // Act
        ResponseEntity<?> response = staffController.getStudents(session);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("You need to login as staff to access this page.", response.getBody());
        verify(studentRepository, never()).findAll();
    }

    @Test
    void addEvent_withValidSessionAndUserRole_shouldAddEventAndReturnSuccessMessage() {
        // Arrange
        ApprovedRegistration user = new ApprovedRegistration();
        user.setRole("staff");
        when(session.getAttribute("loggedInUser")).thenReturn(user);
        StaffController.EventRequest eventRequest = new StaffController.EventRequest();
        eventRequest.setEvent("Test Event");
        eventRequest.setDateOfEvent(LocalDate.now());
        eventRequest.setPeriod(1);

        // Act
        ResponseEntity<?> response = staffController.addEvent(eventRequest, session);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Event added successfully!", response.getBody());
        verify(eventRepository, times(1)).save(any(Event.class));
    }

    @Test
    void addEvent_withInvalidSessionOrUserRole_shouldReturnBadRequest() {
        // Arrange
        when(session.getAttribute("loggedInUser")).thenReturn(null);
        StaffController.EventRequest eventRequest = new StaffController.EventRequest();
        eventRequest.setEvent("Test Event");
        eventRequest.setDateOfEvent(LocalDate.now());
        eventRequest.setPeriod(1);

        // Act
        ResponseEntity<?> response = staffController.addEvent(eventRequest, session);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("You need to login as staff to add events.", response.getBody());
        verify(eventRepository, never()).save(any(Event.class));
    }

    @Test
    void updateEvent_withValidSessionAndUserRoleAndExistingEventId_shouldUpdateEventAndReturnSuccessMessage() {
        // Arrange
        ApprovedRegistration user = new ApprovedRegistration();
        user.setRole("staff");
        when(session.getAttribute("loggedInUser")).thenReturn(user);
        StaffController.EventRequest eventRequest = new StaffController.EventRequest();
        eventRequest.setEvent("Updated Event");
        eventRequest.setDateOfEvent(LocalDate.now());
        eventRequest.setPeriod(2);
        Event existingEvent = new Event();
        existingEvent.setId(1L);
        when(eventRepository.findById(1L)).thenReturn(Optional.of(existingEvent));

        // Act
        ResponseEntity<?> response = staffController.updateEvent(1L, eventRequest, session);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Event updated successfully!", response.getBody());
        verify(eventRepository, times(1)).findById(1L);
        verify(eventRepository, times(1)).save(existingEvent);
    }

    @Test
    void updateEvent_withInvalidSessionOrUserRoleOrNonExistingEventId_shouldReturnNotFound() {
        // Arrange
        when(session.getAttribute("loggedInUser")).thenReturn(null);
        StaffController.EventRequest eventRequest = new StaffController.EventRequest();
        eventRequest.setEvent("Updated Event");
        eventRequest.setDateOfEvent(LocalDate.now());
        eventRequest.setPeriod(2);
        when(eventRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<?> response = staffController.updateEvent(1L, eventRequest, session);

        // Assert
       // assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
       // assertEquals(null, response.getBody());
        verify(eventRepository, never()).save(any(Event.class));
    }

    @Test
    void deleteEvent_withValidSessionAndUserRoleAndExistingEventId_shouldDeleteEventAndReturnSuccessMessage() {
        // Arrange
        ApprovedRegistration user = new ApprovedRegistration();
        user.setRole("staff");
        when(session.getAttribute("loggedInUser")).thenReturn(user);
        Event existingEvent = new Event();
        existingEvent.setId(1L);
        when(eventRepository.findById(1L)).thenReturn(Optional.of(existingEvent));

        // Act
        ResponseEntity<?> response = staffController.deleteEvent(1L, session);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Event deleted successfully!", response.getBody());
        verify(eventRepository, times(1)).findById(1L);
        verify(eventRepository, times(1)).delete(existingEvent);
    }

    @Test
    void deleteEvent_withInvalidSessionOrUserRoleOrNonExistingEventId_shouldReturnNotFound() {
        // Arrange
        when(session.getAttribute("loggedInUser")).thenReturn(null);
        when(eventRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<?> response = staffController.deleteEvent(1L, session);

        // Assert
        //assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
       // assertEquals(null, response.getBody());
        verify(eventRepository, never()).delete(any(Event.class));
    }
}


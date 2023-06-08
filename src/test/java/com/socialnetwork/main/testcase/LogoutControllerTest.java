package com.socialnetwork.main.testcase;
//
//public class LogoutControllerTest {
//
//}
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.socialnetwork.main.controller.LogoutController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LogoutControllerTest {

    @Mock
    private HttpSession session;

    @InjectMocks
    private LogoutController logoutController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void logout_withLoggedInUser_shouldReturnLogoutSuccessful() {
        // Arrange
        when(session.getAttribute("loggedInUser")).thenReturn(new Object());

        // Act
        ResponseEntity<String> response = logoutController.logout(session);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Logout successful", response.getBody());
        verify(session, times(1)).invalidate();
    }

    @Test
    public void logout_withNoLoggedInUser_shouldReturnUserNotLoggedIn() {
        // Arrange
        when(session.getAttribute("loggedInUser")).thenReturn(null);

        // Act
        ResponseEntity<String> response = logoutController.logout(session);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User is not logged in", response.getBody());
        verify(session, never()).invalidate();
    }

//    @Test
//    public void logout_withException_shouldReturnInternalServerError() {
//        // Arrange
//        when(session.getAttribute("loggedInUser")).thenReturn(new Object());
//        doThrow(Exception.class).when(session).invalidate();
//
//        // Act
//        ResponseEntity<String> response = logoutController.logout(session);
//
//        // Assert
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//        assertEquals("Error occurred while logging out", response.getBody());
//        verify(session, times(1)).invalidate();
//    }
}

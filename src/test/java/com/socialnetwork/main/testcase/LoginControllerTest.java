package com.socialnetwork.main.testcase;

//public class LoginControllerTest {
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

import com.socialnetwork.main.controller.LoginController;
import com.socialnetwork.main.model.ApprovedRegistration;
import com.socialnetwork.main.repository.ApprovedRegistrationRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LoginControllerTest {

    @Mock
    private ApprovedRegistrationRepository approvedRegistrationRepository;

    @InjectMocks
    private LoginController loginController;

    private HttpSession session;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        session = mock(HttpSession.class);
    }

    @Test
    public void loginUser_withValidCredentials_shouldReturnLoginSuccessful() {
        // Arrange
        LoginController.LoginCredentials loginCredentials = new LoginController.LoginCredentials("username", "password", "role");

        ApprovedRegistration user = new ApprovedRegistration();
        user.setUsername("username");
        user.setPassword("password");
        user.setRole("role");

        when(approvedRegistrationRepository.findByUsernameAndPasswordAndRole(loginCredentials.getUsername(), loginCredentials.getPassword(), loginCredentials.getRole())).thenReturn(user);

        // Act
        ResponseEntity<String> response = loginController.loginUser(loginCredentials, session);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Login successful!", response.getBody());
        verify(session, times(1)).setAttribute("loggedInUser", user);
    }

    @Test
    public void loginUser_withInvalidCredentials_shouldReturnInvalidLoginCredentials() {
        // Arrange
        LoginController.LoginCredentials loginCredentials = new LoginController.LoginCredentials("username", "password", "role");

        when(approvedRegistrationRepository.findByUsernameAndPasswordAndRole(loginCredentials.getUsername(), loginCredentials.getPassword(), loginCredentials.getRole())).thenReturn(null);

        // Act
        ResponseEntity<String> response = loginController.loginUser(loginCredentials, session);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid login credentials.", response.getBody());
        verify(session, never()).setAttribute(anyString(), any());
    }
}

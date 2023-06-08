package com.socialnetwork.main.testcase;

//public class RegistrationControllerTest {
//
//}
//package com.socialnetwork.main.controller;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import com.socialnetwork.main.controller.RegistrationController;
//import com.socialnetwork.main.model.RegisteredTry;
//import com.socialnetwork.main.repository.RegisteredTryRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//public class RegistrationControllerTest {
//
//    @Mock
//    private RegisteredTryRepository registeredTryRepository;
//
//    @InjectMocks
//    private RegistrationController registrationController;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void registerUser_withValidRegisteredTry_shouldReturnSuccessMessage() {
//        // Arrange
//        RegisteredTry registeredTry = new RegisteredTry();
//
//        // Act
//        ResponseEntity<String> response = registrationController.registerUser(registeredTry);
//
//        // Assert
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("login successfully", response.getBody());
//        verify(registeredTryRepository, times(1)).save(registeredTry);
//    }
//}

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.socialnetwork.main.controller.RegistrationController;
import com.socialnetwork.main.model.RegisteredTry;
import com.socialnetwork.main.repository.RegisteredTryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RegistrationControllerTest {

    @Mock
    private RegisteredTryRepository registeredTryRepository;

    @InjectMocks
    private RegistrationController registrationController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_withValidRegisteredTry_shouldReturnSuccessMessage() {
        // Arrange
        RegisteredTry registeredTry = new RegisteredTry();

        // Act
        String response = registrationController.registerUser(registeredTry);

        // Assert
        assertEquals("login succesfully", response);
        verify(registeredTryRepository, times(1)).save(registeredTry);
    }
}

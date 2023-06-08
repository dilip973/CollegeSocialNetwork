package com.socialnetwork.main.testcase;

//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//@ExtendWith(MockitoExtension.class)
//public class AdminControllerTest {
//
//}
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import com.socialnetwork.main.controller.AdminController;
import com.socialnetwork.main.model.ApprovedNewsFeed;
import com.socialnetwork.main.model.ApprovedRegistration;
import com.socialnetwork.main.model.Faculty;
import com.socialnetwork.main.model.NewsFeed;
import com.socialnetwork.main.model.RegisteredTry;
import com.socialnetwork.main.repository.ApprovedNewsFeedRepository;
import com.socialnetwork.main.repository.ApprovedRegistrationRepository;
import com.socialnetwork.main.repository.FacultyRepository;
import com.socialnetwork.main.repository.NewsFeedRepository;
import com.socialnetwork.main.repository.RegisteredTryRepository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AdminControllerTest {

    @Mock
    private RegisteredTryRepository registeredTryRepository;

    @Mock
    private ApprovedRegistrationRepository approvedRegistrationRepository;

    @Mock
    private NewsFeedRepository newsFeedRepository;

    @Mock
    private FacultyRepository facultyRepository;

    @Mock
    private ApprovedNewsFeedRepository approvedNewsFeedRepository;

    @Mock
    private HttpSession session;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUsers_withAdminUser_shouldReturnUsers() {
        // Arrange
        ApprovedRegistration adminUser = new ApprovedRegistration();
        adminUser.setRole("admin");
        List<RegisteredTry> userList = new ArrayList<>();
        userList.add(new RegisteredTry());

        when(session.getAttribute("loggedInUser")).thenReturn(adminUser);
        when(registeredTryRepository.findAll()).thenReturn(userList);

        // Act
        ResponseEntity<?> response = adminController.getUsers(session);

        // Assert
        assertEquals(ResponseEntity.ok(userList), response);
        verify(registeredTryRepository, times(1)).findAll();
    }

    @Test
    void getUsers_withNonAdminUser_shouldReturnBadRequest() {
        // Arrange
        ApprovedRegistration nonAdminUser = new ApprovedRegistration();
        nonAdminUser.setRole("user");

        when(session.getAttribute("loggedInUser")).thenReturn(nonAdminUser);

        // Act
        ResponseEntity<?> response = adminController.getUsers(session);

        // Assert
        assertEquals(ResponseEntity.badRequest().body("Access denied. Please login as admin."), response);
        verify(registeredTryRepository, never()).findAll();
    }

    @Test
    void approveUser_withValidUserIdAndAdminUser_shouldApproveUser() {
        // Arrange
        ApprovedRegistration adminUser = new ApprovedRegistration();
        adminUser.setRole("admin");
        RegisteredTry user = new RegisteredTry();
        user.setId(1L);

        when(session.getAttribute("loggedInUser")).thenReturn(adminUser);
        when(registeredTryRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act
        ResponseEntity<?> response = adminController.approveUser(1L, session);

        // Assert
        assertEquals(ResponseEntity.ok("User " + user.getUsername() + " has been approved and added to the approved registrations."), response);
        verify(approvedRegistrationRepository, times(1)).save(any(ApprovedRegistration.class));
        verify(registeredTryRepository, times(1)).delete(user);
    }

    @Test
    void approveUser_withInvalidUserId_shouldReturnBadRequest() {
        // Arrange
        ApprovedRegistration adminUser = new ApprovedRegistration();
        adminUser.setRole("admin");

        when(session.getAttribute("loggedInUser")).thenReturn(adminUser);
        when(registeredTryRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<?> response = adminController.approveUser(1L, session);

        // Assert
        assertEquals(ResponseEntity.badRequest().body("User not found with ID 1."), response);
        verify(approvedRegistrationRepository, never()).save(any(ApprovedRegistration.class));
        verify(registeredTryRepository, never()).delete(any(RegisteredTry.class));
    }

    @Test
    void approveUser_withNonAdminUser_shouldReturnBadRequest() {
        // Arrange
        ApprovedRegistration nonAdminUser = new ApprovedRegistration();
        nonAdminUser.setRole("user");

        when(session.getAttribute("loggedInUser")).thenReturn(nonAdminUser);

        // Act
        ResponseEntity<?> response = adminController.approveUser(1L, session);

        // Assert
        assertEquals(ResponseEntity.badRequest().body("Access denied. Please login as admin."), response);
        verify(registeredTryRepository, never()).findById(anyLong());
        verify(approvedRegistrationRepository, never()).save(any(ApprovedRegistration.class));
        verify(registeredTryRepository, never()).delete(any(RegisteredTry.class));
    }

    // Add more test methods for other controller methods
    @Test
    void getNewsFeed_withAdminUser_shouldReturnNewsFeed() {
        // Arrange
        ApprovedRegistration adminUser = new ApprovedRegistration();
        adminUser.setRole("admin");
        List<NewsFeed> newsFeedList = new ArrayList<>();
        newsFeedList.add(new NewsFeed());

        when(session.getAttribute("loggedInUser")).thenReturn(adminUser);
        when(newsFeedRepository.findAll()).thenReturn(newsFeedList);

        // Act
        ResponseEntity<?> response = adminController.getNewsFeed(session);

        // Assert
        assertEquals(ResponseEntity.ok(newsFeedList), response);
        verify(newsFeedRepository, times(1)).findAll();
    }

    @Test
    void getNewsFeed_withNonAdminUser_shouldReturnBadRequest() {
        // Arrange
        ApprovedRegistration nonAdminUser = new ApprovedRegistration();
        nonAdminUser.setRole("user");

        when(session.getAttribute("loggedInUser")).thenReturn(nonAdminUser);

        // Act
        ResponseEntity<?> response = adminController.getNewsFeed(session);

        // Assert
        assertEquals(ResponseEntity.badRequest().body("Access denied. Please login as admin."), response);
        verify(newsFeedRepository, never()).findAll();
    }

    @Test
    void addFaculty_withValidFacultyAndAdminUser_shouldAddFaculty() {
        // Arrange
        ApprovedRegistration adminUser = new ApprovedRegistration();
        adminUser.setRole("admin");
        Faculty faculty = new Faculty();

        when(session.getAttribute("loggedInUser")).thenReturn(adminUser);

        // Act
        ResponseEntity<?> response = adminController.addFaculty(faculty, session);

        // Assert
        assertEquals(ResponseEntity.ok("Faculty " + faculty.getName() + " has been added to the database."), response);
        verify(facultyRepository, times(1)).save(faculty);
    }

    @Test
    void addFaculty_withNonAdminUser_shouldReturnBadRequest() {
        // Arrange
        ApprovedRegistration nonAdminUser = new ApprovedRegistration();
        nonAdminUser.setRole("user");
        Faculty faculty = new Faculty();

        when(session.getAttribute("loggedInUser")).thenReturn(nonAdminUser);

        // Act
        ResponseEntity<?> response = adminController.addFaculty(faculty, session);

        // Assert
        assertEquals(ResponseEntity.badRequest().body("Access denied. Please login as admin."), response);
        verify(facultyRepository, never()).save(any(Faculty.class));
    }

    @Test
    void getAllFaculty_withAdminUser_shouldReturnAllFaculty() {
        // Arrange
        ApprovedRegistration adminUser = new ApprovedRegistration();
        adminUser.setRole("admin");
        List<Faculty> facultyList = new ArrayList<>();
        facultyList.add(new Faculty());

        when(session.getAttribute("loggedInUser")).thenReturn(adminUser);
        when(facultyRepository.findAll()).thenReturn(facultyList);

        // Act
        ResponseEntity<?> response = adminController.getAllFaculty(session);

        // Assert
        assertEquals(ResponseEntity.ok(facultyList), response);
        verify(facultyRepository, times(1)).findAll();
    }

    @Test
    void getAllFaculty_withNonAdminUser_shouldReturnBadRequest() {
        // Arrange
        ApprovedRegistration nonAdminUser = new ApprovedRegistration();
        nonAdminUser.setRole("user");

        when(session.getAttribute("loggedInUser")).thenReturn(nonAdminUser);

        // Act
        ResponseEntity<?> response = adminController.getAllFaculty(session);

        // Assert
        assertEquals(ResponseEntity.badRequest().body("Access denied. Please login as admin."), response);
        verify(facultyRepository, never()).findAll();
    }

    @Test
    void updateFaculty_withValidFacultyIdAndFacultyDataAndAdminUser_shouldUpdateFaculty() {
        // Arrange
        ApprovedRegistration adminUser = new ApprovedRegistration();
        adminUser.setRole("admin");
        Long facultyId = 1L;
        Faculty facultyRequest = new Faculty();
        facultyRequest.setName("New Name");

        Optional<Faculty> facultyOptional = Optional.of(new Faculty());

        when(session.getAttribute("loggedInUser")).thenReturn(adminUser);
        when(facultyRepository.findById(facultyId)).thenReturn(facultyOptional);

        // Act
        ResponseEntity<?> response = adminController.updateFaculty(facultyId, facultyRequest, session);

        // Assert
        assertEquals(ResponseEntity.ok("Faculty " + facultyRequest.getName() + " has been updated successfully."), response);
        verify(facultyRepository, times(1)).findById(facultyId);
        verify(facultyRepository, times(1)).save(facultyOptional.get());
    }

    @Test
    void updateFaculty_withInvalidFacultyId_shouldReturnBadRequest() {
        // Arrange
        ApprovedRegistration adminUser = new ApprovedRegistration();
        adminUser.setRole("admin");
        Long facultyId = 1L;
        Faculty facultyRequest = new Faculty();
        facultyRequest.setName("New Name");

        when(session.getAttribute("loggedInUser")).thenReturn(adminUser);
        when(facultyRepository.findById(facultyId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<?> response = adminController.updateFaculty(facultyId, facultyRequest, session);

        // Assert
        assertEquals(ResponseEntity.badRequest().body("Faculty not found with id: " + facultyId), response);
        verify(facultyRepository, times(1)).findById(facultyId);
        verify(facultyRepository, never()).save(any(Faculty.class));
    }

    @Test
    void updateFaculty_withNonAdminUser_shouldReturnBadRequest() {
        // Arrange
        ApprovedRegistration nonAdminUser = new ApprovedRegistration();
        nonAdminUser.setRole("user");
        Long facultyId = 1L;
        Faculty facultyRequest = new Faculty();

        when(session.getAttribute("loggedInUser")).thenReturn(nonAdminUser);

        // Act
        ResponseEntity<?> response = adminController.updateFaculty(facultyId, facultyRequest, session);

        // Assert
        assertEquals(ResponseEntity.badRequest().body("Access denied. Please login as admin."), response);
        verify(facultyRepository, never()).findById(anyLong());
        verify(facultyRepository, never()).save(any(Faculty.class));
    }

    @Test
    void deleteFacultyById_withValidFacultyIdAndAdminUser_shouldDeleteFaculty() {
        // Arrange
        ApprovedRegistration adminUser = new ApprovedRegistration();
        adminUser.setRole("admin");
        Long facultyId = 1L;

        Optional<Faculty> facultyOptional = Optional.of(new Faculty());

        when(session.getAttribute("loggedInUser")).thenReturn(adminUser);
        when(facultyRepository.findById(facultyId)).thenReturn(facultyOptional);

        // Act
        ResponseEntity<?> response = adminController.deleteFacultyById(facultyId, session);

        // Assert
        assertEquals(ResponseEntity.ok("Faculty with ID " + facultyId + " has been deleted."), response);
        verify(facultyRepository, times(1)).findById(facultyId);
        verify(facultyRepository, times(1)).delete(facultyOptional.get());
    }

    @Test
    void deleteFacultyById_withInvalidFacultyId_shouldReturnBadRequest() {
        // Arrange
        ApprovedRegistration adminUser = new ApprovedRegistration();
        adminUser.setRole("admin");
        Long facultyId = 1L;

        when(session.getAttribute("loggedInUser")).thenReturn(adminUser);
        when(facultyRepository.findById(facultyId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<?> response = adminController.deleteFacultyById(facultyId, session);

        // Assert
        assertEquals(ResponseEntity.badRequest().body("Faculty not found with ID " + facultyId + "."), response);
        verify(facultyRepository, times(1)).findById(facultyId);
        verify(facultyRepository, never()).delete(any(Faculty.class));
    }

    @Test
    void deleteFacultyById_withNonAdminUser_shouldReturnBadRequest() {
        // Arrange
        ApprovedRegistration nonAdminUser = new ApprovedRegistration();
        nonAdminUser.setRole("user");
        Long facultyId = 1L;

        when(session.getAttribute("loggedInUser")).thenReturn(nonAdminUser);

        // Act
        ResponseEntity<?> response = adminController.deleteFacultyById(facultyId, session);

        // Assert
        assertEquals(ResponseEntity.badRequest().body("Access denied. Please login as admin."), response);
        verify(facultyRepository, never()).findById(anyLong());
        verify(facultyRepository, never()).delete(any(Faculty.class));
    }

    @Test
    void approveNewsFeed_withValidNewsFeedIdAndAdminUser_shouldApproveNewsFeed() {
        // Arrange
        ApprovedRegistration adminUser = new ApprovedRegistration();
        adminUser.setRole("admin");
        Long newsFeedId = 1L;

        Optional<NewsFeed> newsFeedOptional = Optional.of(new NewsFeed());

        when(session.getAttribute("loggedInUser")).thenReturn(adminUser);
        when(newsFeedRepository.findById(newsFeedId)).thenReturn(newsFeedOptional);

        // Act
        ResponseEntity<?> response = adminController.approveNewsFeed(newsFeedId, session);

        // Assert
        assertEquals(ResponseEntity.ok("News feed with ID " + newsFeedId + " has been approved and moved to the approved news feed database."), response);
        verify(approvedNewsFeedRepository, times(1)).save(any(ApprovedNewsFeed.class));
        verify(newsFeedRepository, times(1)).deleteById(newsFeedId);
    }

    @Test
    void approveNewsFeed_withInvalidNewsFeedId_shouldReturnBadRequest() {
        // Arrange
        ApprovedRegistration adminUser = new ApprovedRegistration();
        adminUser.setRole("admin");
        Long newsFeedId = 1L;

        when(session.getAttribute("loggedInUser")).thenReturn(adminUser);
        when(newsFeedRepository.findById(newsFeedId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<?> response = adminController.approveNewsFeed(newsFeedId, session);

        // Assert
        assertEquals(ResponseEntity.badRequest().body("News feed with ID " + newsFeedId + " does not exist."), response);
        verify(approvedNewsFeedRepository, never()).save(any(ApprovedNewsFeed.class));
        verify(newsFeedRepository, never()).deleteById(anyLong());
    }

    @Test
    void approveNewsFeed_withNonAdminUser_shouldReturnBadRequest() {
        // Arrange
        ApprovedRegistration nonAdminUser = new ApprovedRegistration();
        nonAdminUser.setRole("user");
        Long newsFeedId = 1L;

        when(session.getAttribute("loggedInUser")).thenReturn(nonAdminUser);

        // Act
        ResponseEntity<?> response = adminController.approveNewsFeed(newsFeedId, session);

        // Assert
        assertEquals(ResponseEntity.badRequest().body("Access denied. Please login as admin."), response);
        verify(approvedNewsFeedRepository, never()).save(any(ApprovedNewsFeed.class));
        verify(newsFeedRepository, never()).deleteById(anyLong());
    }

}




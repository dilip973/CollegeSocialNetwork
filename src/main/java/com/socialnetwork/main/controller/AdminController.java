package com.socialnetwork.main.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RegisteredTryRepository registeredTryRepository;

    @Autowired
    private ApprovedRegistrationRepository approvedRegistrationRepository;
    
    @Autowired
    private NewsFeedRepository newsFeedRepository;
    
    @Autowired
    private FacultyRepository facultyRepository;
    
    @Autowired
    private ApprovedNewsFeedRepository approvedNewsFeedRepository;

    
    @GetMapping("/users")
    public ResponseEntity<?> getUsers(HttpSession session) {
    	ApprovedRegistration user = (ApprovedRegistration) session.getAttribute("loggedInUser");
        if (user != null && user.getRole().equals("admin")) {
            Iterable<RegisteredTry> users = registeredTryRepository.findAll();
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.badRequest().body("Access denied. Please login as admin.");
        }
    }
    @GetMapping("/newsfeed")
    public ResponseEntity<?> getNewsFeed(HttpSession session) {
    	ApprovedRegistration user = (ApprovedRegistration) session.getAttribute("loggedInUser");
        if (user != null && user.getRole().equals("admin")) {
            Iterable<NewsFeed> newsFeed = newsFeedRepository.findAll();
            return ResponseEntity.ok(newsFeed);
        } else {
            return ResponseEntity.badRequest().body("Access denied. Please login as admin.");
        }
    }

    @PostMapping("/users/{id}/approve")
    public ResponseEntity<?> approveUser(@PathVariable("id") Long id, HttpSession session) {
    	ApprovedRegistration admin = (ApprovedRegistration) session.getAttribute("loggedInUser");
        if (admin != null && admin.getRole().equals("admin")) {
            RegisteredTry user = registeredTryRepository.findById(id).orElse(null);
            if (user != null) {
                ApprovedRegistration approvedRegistration = new ApprovedRegistration();
                approvedRegistration.setFirstName(user.getFirstName());
                approvedRegistration.setLastName(user.getLastName());
                approvedRegistration.setUsername(user.getUsername());
                approvedRegistration.setPassword(user.getPassword());
                approvedRegistration.setRole(user.getRole());
                approvedRegistrationRepository.save(approvedRegistration);
                registeredTryRepository.delete(user);
                return ResponseEntity.ok("User " + user.getUsername() + " has been approved and added to the approved registrations.");
            } else {
                return ResponseEntity.badRequest().body("User not found with ID " + id + ".");
            }
        } else {
            return ResponseEntity.badRequest().body("Access denied. Please login as admin.");
        }     
    }
    @PostMapping("/faculty")
    public ResponseEntity<?> addFaculty(@RequestBody Faculty faculty, HttpSession session) {
    	ApprovedRegistration admin = (ApprovedRegistration) session.getAttribute("loggedInUser");
        if (admin != null && admin.getRole().equals("admin")) {
            facultyRepository.save(faculty);
            return ResponseEntity.ok("Faculty " + faculty.getName() + " has been added to the database.");
        } else {
            return ResponseEntity.badRequest().body("Access denied. Please login as admin.");
        }
    }
    @GetMapping("/faculty")
    public ResponseEntity<?> getAllFaculty(HttpSession session) {
        ApprovedRegistration admin = (ApprovedRegistration) session.getAttribute("loggedInUser");
        if (admin != null && admin.getRole().equals("admin")) {
            Iterable<Faculty> facultyList = facultyRepository.findAll();
            return ResponseEntity.ok(facultyList);
        } else {
            return ResponseEntity.badRequest().body("Access denied. Please login as admin.");
        }
    }
    @PutMapping("/faculty/{id}")
    public ResponseEntity<?> updateFaculty(@PathVariable Long id, @RequestBody Faculty facultyRequest, HttpSession session) {
        ApprovedRegistration admin = (ApprovedRegistration) session.getAttribute("loggedInUser");
        if (admin != null && admin.getRole().equals("admin")) {
            Optional<Faculty> facultyOptional = facultyRepository.findById(id);
            if (facultyOptional.isPresent()) {
                Faculty faculty = facultyOptional.get();
                faculty.setName(facultyRequest.getName());
                faculty.setDepartment(facultyRequest.getDepartment());
                facultyRepository.save(faculty);
                return ResponseEntity.ok("Faculty " + faculty.getName() + " has been updated successfully.");
            } else {
                return ResponseEntity.badRequest().body("Faculty not found with id: " + id);
            }
        } else {
            return ResponseEntity.badRequest().body("Access denied. Please login as admin.");
        }
    }

    @DeleteMapping("/faculty/{id}")
    public ResponseEntity<?> deleteFacultyById(@PathVariable("id") Long id, HttpSession session) {
        ApprovedRegistration admin = (ApprovedRegistration) session.getAttribute("loggedInUser");
        if (admin != null && admin.getRole().equals("admin")) {
            Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
            if (optionalFaculty.isPresent()) {
                Faculty faculty = optionalFaculty.get();
                facultyRepository.delete(faculty);
                return ResponseEntity.ok("Faculty with ID " + id + " has been deleted.");
            } else {
                return ResponseEntity.badRequest().body("Faculty not found with ID " + id + ".");
            }
        } else {
            return ResponseEntity.badRequest().body("Access denied. Please login as admin.");
        }
    }
    @PostMapping("/news-feed/approve/{id}")
    public ResponseEntity<?> approveNewsFeed(@PathVariable Long id, HttpSession session) {
        ApprovedRegistration admin = (ApprovedRegistration) session.getAttribute("loggedInUser");
        if (admin != null && admin.getRole().equals("admin")) {
            Optional<NewsFeed> newsFeed = newsFeedRepository.findById(id);
            if (newsFeed.isPresent()) {
                ApprovedNewsFeed approvedNewsFeed = new ApprovedNewsFeed();
                approvedNewsFeed.setTitle(newsFeed.get().getTitle());
                approvedNewsFeed.setContent(newsFeed.get().getContent());
                approvedNewsFeed.setImageData(newsFeed.get().getImageData());
                approvedNewsFeed.setPostedDate(newsFeed.get().getPostedDate());
                approvedNewsFeed.setStudentId(newsFeed.get().getStudentId());
                approvedNewsFeed.setStudentName(newsFeed.get().getStudentName());
                
                approvedNewsFeedRepository.save(approvedNewsFeed);
                newsFeedRepository.deleteById(id);
                
                return ResponseEntity.ok("News feed with ID " + id + " has been approved and moved to the approved news feed database.");
            } else {
                return ResponseEntity.badRequest().body("News feed with ID " + id + " does not exist.");
            }
        } else {
            return ResponseEntity.badRequest().body("Access denied. Please login as admin.");
        }
    }



}


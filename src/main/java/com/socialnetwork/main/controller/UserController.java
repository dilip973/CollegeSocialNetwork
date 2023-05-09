package com.socialnetwork.main.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialnetwork.main.model.ApprovedNewsFeed;
import com.socialnetwork.main.model.ApprovedRegistration;
import com.socialnetwork.main.model.Event;
import com.socialnetwork.main.model.Faculty;
import com.socialnetwork.main.model.Posts;
import com.socialnetwork.main.model.Student;
import com.socialnetwork.main.repository.ApprovedNewsFeedRepository;
import com.socialnetwork.main.repository.ApprovedRegistrationRepository;
import com.socialnetwork.main.repository.EventRepository;
import com.socialnetwork.main.repository.FacultyRepository;
import com.socialnetwork.main.repository.PostsRepository;
import com.socialnetwork.main.repository.StudentRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ApprovedRegistrationRepository approvedRegistrationRepository;

    @Autowired
    private ApprovedNewsFeedRepository approvedNewsFeedRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private PostsRepository postRepository;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents(HttpSession session) {
        if (isLoggedIn(session)) {
            List<Student> students = studentRepository.findAll();
            return ResponseEntity.ok(students);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/registrations")
    public ResponseEntity<List<ApprovedRegistration>> getAllRegistrations(HttpSession session) {
        if (isLoggedIn(session)) {
            List<ApprovedRegistration> registrations = approvedRegistrationRepository.findAll();
            return ResponseEntity.ok(registrations);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/newsfeed")
    public ResponseEntity<List<ApprovedNewsFeed>> getAllNewsFeed(HttpSession session) {
        if (isLoggedIn(session)) {
            List<ApprovedNewsFeed> newsFeed = approvedNewsFeedRepository.findAll();
            return ResponseEntity.ok(newsFeed);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents(HttpSession session) {
        if (isLoggedIn(session)) {
            List<Event> events = eventRepository.findAll();
            return ResponseEntity.ok(events);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/faculties")
    public ResponseEntity<List<Faculty>> getAllFaculties(HttpSession session) {
        if (isLoggedIn(session)) {
            List<Faculty> faculties = facultyRepository.findAll();
            return ResponseEntity.ok(faculties);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Posts>> getAllPosts(HttpSession session) {
        if (isLoggedIn(session)) {
            List<Posts> posts = postRepository.findAll();
            return ResponseEntity.ok(posts);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("loggedInUser") != null;
    }
}

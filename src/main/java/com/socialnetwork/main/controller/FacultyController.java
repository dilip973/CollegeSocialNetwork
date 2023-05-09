package com.socialnetwork.main.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialnetwork.main.model.ApprovedRegistration;
import com.socialnetwork.main.model.Student;
import com.socialnetwork.main.repository.StudentRepository;

@RestController
@RequestMapping("/students")
public class FacultyController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public ResponseEntity<?> getStudents(HttpSession session) {
        ApprovedRegistration user = (ApprovedRegistration) session.getAttribute("loggedInUser");
        if (user != null && user.getRole().equals("faculty")) {
            Iterable<Student> students = studentRepository.findAll();
            return ResponseEntity.ok(students);
        } else {
            return ResponseEntity.badRequest().body("Access denied.");
        }
    }
}

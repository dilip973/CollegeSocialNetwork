package com.socialnetwork.main.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialnetwork.main.model.ApprovedRegistration;
import com.socialnetwork.main.model.Posts;
import com.socialnetwork.main.repository.PostsRepository;
import com.socialnetwork.main.repository.StudentRepository;



@RestController
@RequestMapping("/students")
public class PlacementController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    public ResponseEntity<String> addStudent(@RequestBody StudentDetails studentDetails, HttpSession session) {
        ApprovedRegistration user = (ApprovedRegistration) session.getAttribute("loggedInUser");
        if (user != null && user.getRole().equals("placement officer")) {
            com.socialnetwork.main.model.Student student = new com.socialnetwork.main.model.Student();
            student.setId(studentDetails.getId());
            student.setName(studentDetails.getName());
            student.setDepartment(studentDetails.getDepartment());
            student.setBacklogs(studentDetails.getBacklogs());
            student.setPercentage(studentDetails.getPercentage());
            studentRepository.save(student);
            return ResponseEntity.ok("Student added successfully!");
        } else {
            return ResponseEntity.badRequest().body("You do not have permission to add a student.");
        }
    }
    @Autowired
    private PostsRepository postsRepository;

    @PostMapping("/add-post")
    public ResponseEntity<String> addPost(@RequestBody PostDetails postDetails, HttpSession session) {
        ApprovedRegistration user = (ApprovedRegistration) session.getAttribute("loggedInUser");
        if (user != null && user.getRole().equals("placement officer")) {
            Posts post = new Posts();
            post.setId(postDetails.getId());
            post.setTitle(postDetails.getTitle());
            post.setContent(postDetails.getContent());
            post.setUsername(user.getUsername());
            post.setPostedDate(new Date());
            postsRepository.save(post);
            return ResponseEntity.ok("Post added successfully!");
        } else {
            return ResponseEntity.badRequest().body("You do not have permission to add a post.");
        }
    }
    @DeleteMapping("/delete-post/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId, HttpSession session) {
        ApprovedRegistration user = (ApprovedRegistration) session.getAttribute("loggedInUser");
        if (user != null && user.getRole().equals("placement officer")) {
            Posts post = postsRepository.findById(postId).orElse(null);
            if (post == null) {
                return ResponseEntity.badRequest().body("Post not found.");
            }
            if (!post.getUsername().equals(user.getUsername())) {
                return ResponseEntity.badRequest().body("You do not have permission to delete this post.");
            }
            postsRepository.delete(post);
            return ResponseEntity.ok("Post deleted successfully!");
        } else {
            return ResponseEntity.badRequest().body("You do not have permission to delete a post.");
        }
    }
    @PutMapping("/update-post/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable Long postId, @RequestBody PostDetails postDetails, HttpSession session) {
        ApprovedRegistration user = (ApprovedRegistration) session.getAttribute("loggedInUser");
        if (user != null && user.getRole().equals("placement officer")) {
            Posts post = postsRepository.findById(postId).orElse(null);
            if (post == null) {
                return ResponseEntity.badRequest().body("Post not found.");
            }
            if (!post.getUsername().equals(user.getUsername())) {
                return ResponseEntity.badRequest().body("You do not have permission to update this post.");
            }
            post.setTitle(postDetails.getTitle());
            post.setContent(postDetails.getContent());
            postsRepository.save(post);
            return ResponseEntity.ok("Post updated successfully!");
        } else {
            return ResponseEntity.badRequest().body("You do not have permission to update a post.");
        }
    }

    static class PostDetails {
        private Long id;
        private String title;
        private String content;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }


    static class StudentDetails {
        private int id;
        private String name;
        private String department;
        private int backlogs;
        private double percentage;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public int getBacklogs() {
            return backlogs;
        }

        public void setBacklogs(int backlogs) {
            this.backlogs = backlogs;
        }

        public double getPercentage() {
            return percentage;
        }

        public void setPercentage(double percentage) {
            this.percentage = percentage;
        }
    }
}

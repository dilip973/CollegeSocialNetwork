package com.socialnetwork.main.controller;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.socialnetwork.main.model.ApprovedNewsFeed;
import com.socialnetwork.main.model.ApprovedRegistration;
import com.socialnetwork.main.model.NewsFeed;
import com.socialnetwork.main.repository.ApprovedNewsFeedRepository;
import com.socialnetwork.main.repository.NewsFeedRepository;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private NewsFeedRepository newsFeedRepository;
    @Autowired
    private ApprovedNewsFeedRepository approvedNewsFeedRepository;

    @PostMapping("/addArticle")
    public ResponseEntity<String> addArticle(@RequestParam("image") MultipartFile image, 
                                              @RequestParam("title") String title, 
                                              @RequestParam("content") String content, 
                                              HttpSession session) {
        ApprovedRegistration user = (ApprovedRegistration) session.getAttribute("loggedInUser");
        if (user != null && "student".equals(user.getRole())) {
            try {
                NewsFeed newsFeed = new NewsFeed();
                newsFeed.setTitle(title);
                newsFeed.setContent(content);
                newsFeed.setPostedDate(new Date());
                newsFeed.setStudentId(user.getId());
                newsFeed.setStudentName(user.getFirstName());
                newsFeed.setImageData(image.getBytes());
                newsFeedRepository.save(newsFeed);
                return ResponseEntity.ok("Article added successfully.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image.");
            }
        } else {
            return ResponseEntity.badRequest().body("Invalid session or user role.");
        }
    }
    @PutMapping("/updateArticle/{id}")
    public ResponseEntity<String> updateArticle(@PathVariable Long id, 
                                                 @RequestParam("image") Optional<MultipartFile> image, 
                                                 @RequestParam("title") String title, 
                                                 @RequestParam("content") String content, 
                                                 HttpSession session) {
        ApprovedRegistration user = (ApprovedRegistration) session.getAttribute("loggedInUser");
        if (user != null && "student".equals(user.getRole())) {
            Optional<ApprovedNewsFeed> optionalNewsFeed = approvedNewsFeedRepository.findById(id);
            if (optionalNewsFeed.isPresent()) {
                ApprovedNewsFeed newsFeed = optionalNewsFeed.get();
                if (newsFeed.getStudentId().equals(user.getId())) {
                    newsFeed.setTitle(title);
                    newsFeed.setContent(content);
                    if (image.isPresent()) {
                        try {
                            newsFeed.setImageData(image.get().getBytes());
                        } catch (Exception e) {
                            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image.");
                        }
                    }
                    approvedNewsFeedRepository.save(newsFeed);
                    return ResponseEntity.ok("Article updated successfully.");
                } else {
                    return ResponseEntity.badRequest().body("You don't have permission to update this article.");
                }
            } else {
                return ResponseEntity.badRequest().body("Article not found.");
            }
        } else {
            return ResponseEntity.badRequest().body("Invalid session or user role.");
        }
    }
    @DeleteMapping("/deleteArticle/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable Long id, HttpSession session) {
        ApprovedRegistration user = (ApprovedRegistration) session.getAttribute("loggedInUser");
        if (user != null && "student".equals(user.getRole())) {
            try {
                approvedNewsFeedRepository.deleteById(id);
                return ResponseEntity.ok("Article deleted successfully.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting article.");
            }
        } else {
            return ResponseEntity.badRequest().body("Invalid session or user role.");
        }
    }
    
}
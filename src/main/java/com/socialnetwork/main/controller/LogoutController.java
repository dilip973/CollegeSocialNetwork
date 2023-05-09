package com.socialnetwork.main.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) { // check if the user is not logged in
            return ResponseEntity.badRequest().body("User is not logged in"); // return error message
        }

        try {
            session.invalidate(); // invalidate the session
            return ResponseEntity.ok("Logout successful"); // return success message
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error occurred while logging out"); // return error message
        }
    }
}
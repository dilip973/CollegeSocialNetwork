package com.socialnetwork.main.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialnetwork.main.model.ApprovedRegistration;
import com.socialnetwork.main.repository.ApprovedRegistrationRepository;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private ApprovedRegistrationRepository approvedRegistrationRepository;

    @PostMapping
    public ResponseEntity<String> loginUser(@RequestBody LoginCredentials loginCredentials, HttpSession session) {
        ApprovedRegistration user = approvedRegistrationRepository.findByUsernameAndPasswordAndRole(loginCredentials.getUsername(), loginCredentials.getPassword(), loginCredentials.getRole());
        if (user != null) {
            session.setAttribute("loggedInUser", user);
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.badRequest().body("Invalid login credentials.");
        }
    }

    static class LoginCredentials {
    	 
        private String username;
 
        private String password;
 
        private String role;

		public LoginCredentials(String username, String password, String role) {
			super();
			this.username = username;
			this.password = password;
			this.role = role;
		}

		public LoginCredentials() {
			super();
			// TODO Auto-generated constructor stub
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}
 
    }
}
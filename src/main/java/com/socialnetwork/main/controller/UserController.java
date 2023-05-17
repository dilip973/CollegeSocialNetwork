package com.socialnetwork.main.controller;
import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialnetwork.main.bto.Message;
import com.socialnetwork.main.model.User;
import com.socialnetwork.main.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody User user) {
        User uDB = userRepository.findByEmailId(user.getEmailId());
        Message m = new Message();

        if(uDB != null) {
            m.setMsg("Email already registered");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        m.setMsg("User registration successful");

        return ResponseEntity.status(HttpStatus.OK).body(m);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user, HttpSession session, HttpServletResponse response) {
        
    	User uDB = userRepository.findByEmailId(user.getEmailId());
        Message m = new Message();

        if (uDB != null && passwordEncoder.matches(user.getPassword(), uDB.getPassword())) {
            session.setAttribute("loggedInUser", uDB);
            Cookie sessionCookie = new Cookie("JSESSIONID", session.getId());
            sessionCookie.setPath("/");
            sessionCookie.setHttpOnly(true);
            sessionCookie.setMaxAge(7 * 24 * 60 * 60); // set to 7 days
            response.addCookie(sessionCookie);
            return ResponseEntity.status(HttpStatus.OK).body(uDB);
        } else {
            m.setMsg("Invalid email or password.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
        }
        
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request, HttpServletResponse response) {
        // Invalidate the session
        request.getSession().invalidate();

        // Clear cookies if necessary
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }

        Message message = new Message();
        message.setMsg("User logged out successfully.");

        return ResponseEntity.ok(message);
    }

    
    @GetMapping("/login")
	public User login(Principal principal) { //it tells us currently loggedIn username
		//if u come to this line, it means that username and password given are valid
		User user  = userRepository.getUserByEmailId(principal.getName());
		return user;
	}
}

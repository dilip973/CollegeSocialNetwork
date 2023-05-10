package com.socialnetwork.main.controller;
import java.security.Principal;

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



@CrossOrigin(origins = {"*"})
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
	
	@GetMapping("/login")
	public User login(Principal principal) {
		User user = userRepository.findByEmailId(principal.getName());
		return user;
	}
}

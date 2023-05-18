package com.socialnetwork.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.socialnetwork.main.model.RegisteredTry;
import com.socialnetwork.main.repository.RegisteredTryRepository;


@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("/api/user/")
public class RegistrationController {
 
    @Autowired
    private RegisteredTryRepository registeredTryRepository;
 
    @PostMapping("/register")
    public String registerUser(@RequestBody RegisteredTry registeredTry) {
        registeredTryRepository.save(registeredTry);
        return "login succesfully";
    }
 
}
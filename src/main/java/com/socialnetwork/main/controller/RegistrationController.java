package com.socialnetwork.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialnetwork.main.model.RegisteredTry;
import com.socialnetwork.main.repository.RegisteredTryRepository;



@RestController
@RequestMapping("/register")
public class RegistrationController {
 
    @Autowired
    private RegisteredTryRepository registeredTryRepository;
 
    @PostMapping
    public void registerUser(@RequestBody RegisteredTry registeredTry) {
        registeredTryRepository.save(registeredTry);
    }
 
}
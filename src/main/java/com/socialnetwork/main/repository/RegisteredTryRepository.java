package com.socialnetwork.main.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.socialnetwork.main.model.RegisteredTry;


public interface RegisteredTryRepository extends JpaRepository<RegisteredTry, Long> {
 
    RegisteredTry findByUsernameAndPasswordAndRole(String username, String password, String role);
 
}
package com.socialnetwork.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialnetwork.main.model.ApprovedRegistration;

@Repository
public interface ApprovedRegistrationRepository extends JpaRepository<ApprovedRegistration, Long> {
    ApprovedRegistration findByUsernameAndPasswordAndRole(String username, String password, String Role);
}
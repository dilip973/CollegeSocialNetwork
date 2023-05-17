package com.socialnetwork.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialnetwork.main.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailId(String emailId);

    User getUserByEmailId(String emailId);
}

package com.socialnetwork.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialnetwork.main.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmailId(String emailId);

	User getUserByEmailId(String username);

}

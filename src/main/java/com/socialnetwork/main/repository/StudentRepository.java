package com.socialnetwork.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialnetwork.main.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
}

package com.spring.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.db.model.Student;

@Repository
public interface UserRepository extends JpaRepository<Student, Integer> {
}

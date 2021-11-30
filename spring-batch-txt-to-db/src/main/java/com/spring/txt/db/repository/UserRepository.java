package com.spring.txt.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.txt.db.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}

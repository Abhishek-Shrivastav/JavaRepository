package com.example.facade;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

	User findByUsername(String username);
}

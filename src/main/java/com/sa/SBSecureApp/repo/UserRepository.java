package com.sa.SBSecureApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.SBSecureApp.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUsername(String username); // getting user details through username
}

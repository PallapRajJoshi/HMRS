package com.hotelmanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelmanagementsystem.model.User;



public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmailAndPassword(String email,String password);
	
	
	
	User findByEmail(String email);

}

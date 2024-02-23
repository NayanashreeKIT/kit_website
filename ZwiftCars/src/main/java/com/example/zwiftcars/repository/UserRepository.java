package com.example.zwiftcars.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.zwiftcars.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmailOrPhoneNumber(String email, String mobileNo);

	
}
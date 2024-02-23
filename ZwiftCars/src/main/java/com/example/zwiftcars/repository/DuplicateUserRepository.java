package com.example.zwiftcars.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.zwiftcars.entity.DuplicateUser;

public interface DuplicateUserRepository extends JpaRepository<DuplicateUser, Long>{
	
	public Optional<DuplicateUser>findByMobileNo(String mobileNo);

}

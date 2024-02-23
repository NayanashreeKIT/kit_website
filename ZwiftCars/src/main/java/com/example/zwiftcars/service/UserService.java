package com.example.zwiftcars.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.zwiftcars.entity.DuplicateUser;
import com.example.zwiftcars.entity.User;

@Service
public interface UserService {
	
	// Save data to DuplicateUser
	public DuplicateUser registerDuplicateUser(DuplicateUser duplicateUser);
	
	//delete records in DuplicateUser
	public void deleteDuplicateUser(String mobileNo);
	
	//Method to save the user
	public User registerUser(String mobileNo);
	
	//Method to fetch the user details
	public List<User> getAllUsers();
	
	//Update user
	public User updateUser(User user, Long id);
		
	// Delete user
	public void deleteUser(Long id);
	
	//login using emailOrMobileNo
	public User findByEmailOrPhoneNumber(String email, String mobileNo);

	


	
	
	

}

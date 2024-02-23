package com.example.zwiftcars.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.zwiftcars.entity.DuplicateUser;
import com.example.zwiftcars.entity.User;
import com.example.zwiftcars.repository.DuplicateUserRepository;
import com.example.zwiftcars.repository.UserRepository;
import com.example.zwiftcars.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DuplicateUserRepository duplicateUserRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcry;
	
	// Save Records to DuplicateUser
	@Override
	public DuplicateUser registerDuplicateUser(DuplicateUser duplicateUser) {
		duplicateUser.setPassword(bcry.encode(duplicateUser.getPassword()));
		return duplicateUserRepository.save(duplicateUser);
	}

    //Save Records to User Table from Duplicate User
	@Override
	public User registerUser(String mobileNo) {
		DuplicateUser dUser = duplicateUserRepository.findByMobileNo(mobileNo).orElseThrow(null);
		
		User user = new User();
		user.setName(dUser.getName());
		user.setEmail(dUser.getEmail());
		user.setMobileNo(dUser.getMobileNo());
		user.setPassword(dUser.getPassword());
		User savedUser = userRepository.save(user);
		return savedUser;
	}
	
	
	//Delete records from DuplicateUser once the otp is valid
   @Override
	public void deleteDuplicateUser(String mobileNo) {
		DuplicateUser dUser = duplicateUserRepository.findByMobileNo(mobileNo).orElseThrow(null);
		duplicateUserRepository.delete(dUser);
	}


   // Get all records from User
	@Override
	public List<User> getAllUsers() {
	return userRepository.findAll();
	}
	
	//Update records in User
	//Update User details
	@Override
	public User updateUser(User user, Long id) {
		User existingUser = userRepository.findById(id).orElseThrow(
				()-> new com.example.zwiftcars.exception.ResourceNotFoundException("User", "id", id));
		existingUser.setName(user.getName());
		existingUser.setEmail(user.getEmail());
		existingUser.setMobileNo(user.getMobileNo());
		existingUser.setPassword(user.getPassword());
		
		//save existing user to DB
		userRepository.save(existingUser);
		return existingUser;
		
	}

	
	//Delete records from User
	@Override
	public void deleteUser(Long id) {
		//check whether the user exist in the DB
		userRepository.findById(id).orElseThrow(
				() -> new com.example.zwiftcars.exception.ResourceNotFoundException("User", "id", id));
		userRepository.deleteById(id);
	}

	//Login Using EmailOrMobileNo
	@Override
	public User findByEmailOrPhoneNumber(String email, String mobileNo) {
		return userRepository.findByEmailOrPhoneNumber(email, mobileNo);


	}

	

	

	
	


}

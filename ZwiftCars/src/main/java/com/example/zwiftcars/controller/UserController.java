package com.example.zwiftcars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.zwiftcars.entity.DuplicateUser;
import com.example.zwiftcars.entity.LoginRequest;
import com.example.zwiftcars.entity.User;
import com.example.zwiftcars.service.UserService;
import com.example.zwiftcars.twilio.OtpResponse;
import com.example.zwiftcars.twilio.OtpValidationRequest;
import com.example.zwiftcars.twilio.SmsService;

import jakarta.validation.Valid;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private SmsService smsService;
	
	//Save the record into DuplicateUser
	@PostMapping("/saveDuplicateUser")
	public ResponseEntity<OtpResponse>saveDuplicateUser(@Valid @RequestBody DuplicateUser duplicateuser){
		System.out.println("Front end data is fetched" + duplicateuser); 
		userService.registerDuplicateUser(duplicateuser);
		
		// otp generation
		OtpResponse otpStatus = smsService.sendSMS(duplicateuser);
		return new ResponseEntity<OtpResponse>(otpStatus,HttpStatusCode.valueOf(200));
	}
	
	@PostMapping("/validateOtp")
	public ResponseEntity<String>saveToUser(@RequestBody OtpValidationRequest otpValidationRequest){
		boolean validateOtp = smsService.validateOtp(otpValidationRequest);
		if (validateOtp==true) {
			// Save record from DuplicateUser to User
			userService.registerUser(otpValidationRequest.getMobileNo());
			
			//Delete record from DuplicateUser after otp validation
			userService.deleteDuplicateUser(otpValidationRequest.getMobileNo());
			return new ResponseEntity<String>("Registration Success",HttpStatusCode.valueOf(200));
		}
		
		return new ResponseEntity<String>("Invalid Otp",HttpStatusCode.valueOf(201));
	}
	
	//Fetch the user details from User
		@GetMapping("/get")
		public List<User> getAllUsers(){
			return userService.getAllUsers();
		}
	
	//Update the user details in User
		//Update User
		@PutMapping("/{id}")
		public ResponseEntity<User> updateUser(@PathVariable("id") Long id
				                              ,@RequestBody User user){
			
			return new ResponseEntity<User>(userService.updateUser(user, id), HttpStatus.OK);
			
		}
	
	//Delete records in User
	@DeleteMapping("/{id}")
		public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
			userService.deleteUser(id);
			return new ResponseEntity<String>("User deleted Successfully",HttpStatus.OK);
		
	}
	
	//Login Page
	//This mapping is used to compare username and password with the entered username and password
//	@PostMapping("/login")
//	 public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//        User user = userService.findByEmailOrPhoneNumber(loginRequest.getIdentifier(), loginRequest.getIdentifier());
//
//        if (user != null && bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
//            // Generate and return a JWT or session token here
//            return ResponseEntity.ok("Login Success");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//    }


}
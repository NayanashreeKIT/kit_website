package com.example.zwiftcars.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.zwiftcars.entity.User;
import com.example.zwiftcars.repository.UserRepository;

@Component
public class CustomUserDetailsService {
	@Autowired
	private UserRepository userRepository;

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userRepository.findByEmail(username).orElseThrow(null);
//
//		return user;
//	}

}

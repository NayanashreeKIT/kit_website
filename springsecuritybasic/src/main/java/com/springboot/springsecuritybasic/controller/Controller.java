package com.springboot.springsecuritybasic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@GetMapping("/welcome")
	public String sayWelcome() {
		return "Welcome to Spring Application With Security";
		
	}

}

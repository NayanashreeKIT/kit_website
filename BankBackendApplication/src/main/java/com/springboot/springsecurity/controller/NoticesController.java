package com.springboot.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticesController {
	@GetMapping("/notice")
	public String getNotices() {
		return "Here are the notices from the Database";
	}

}

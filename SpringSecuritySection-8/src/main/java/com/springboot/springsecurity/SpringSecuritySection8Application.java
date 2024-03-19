package com.springboot.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity(debug = true)
public class SpringSecuritySection8Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecuritySection8Application.class, args);
	}

}

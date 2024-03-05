package com.springboot.springsecurity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
public class ProjectSecurityConfiguration {
	
	//Custom security configuration
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                .requestMatchers("/notice", "/contact").permitAll()).formLogin(withDefaults()).httpBasic(withDefaults());
		     return http.build();
	}
	
	
	//Deny all request
//	@Bean
//	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
//        http.authorizeHttpRequests(requests -> requests.anyRequest().denyAll()).formLogin(withDefaults()).httpBasic(withDefaults());
//		return http.build();
//	}
	
	//Permit all the request
//	@Bean
//	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
//        http.authorizeHttpRequests(requests -> requests.anyRequest().permitAll()).formLogin(withDefaults()).httpBasic(withDefaults());
//		return http.build();
//	}

}

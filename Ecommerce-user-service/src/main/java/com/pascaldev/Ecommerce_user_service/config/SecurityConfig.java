package com.pascaldev.Ecommerce_user_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import com.pascaldev.Ecommerce_user_service.serviceImpl.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
public class SecurityConfig {
//	private final CustomUserDetailsService  userDetailsService;
//	
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//		return http
//				.csrf(AbstractHttpConfigurer::disable)
//				.authorizeHttpRequests(auth -> {
//			auth.requestMatchers("/").permitAll();
//			auth.anyRequest().authenticated();
//		})
//				.build();
//	}
//	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder)throws Exception{
//		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//		return authenticationManagerBuilder.build();
//	}
//	

}

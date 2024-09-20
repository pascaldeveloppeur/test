package com.pascaldev.Ecommerce_user_service.serviceImpl;

import java.util.Collections;


import org.springframework.stereotype.Service;

import com.pascaldev.Ecommerce_user_service.model.User;
import com.pascaldev.Ecommerce_user_service.repository.UserRepository;
import com.pascaldev.Ecommerce_utils_service.model.PascalDevException;

import lombok.RequiredArgsConstructor;

@Service
//@RequiredArgsConstructor
public class CustomUserDetailsService  {

	//private final UserRepository userRepository;
	//
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userRepository.findByName(username);
//		if(user == null) {
//			throw new PascalDevException("user not found with username:" + username);
//		}
//		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(user.getUsername())));
//		
//	}

}

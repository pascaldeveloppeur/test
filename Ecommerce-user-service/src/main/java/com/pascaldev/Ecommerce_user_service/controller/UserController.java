package com.pascaldev.Ecommerce_user_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pascaldev.Ecommerce_user_service.Dto.UserDto;
import com.pascaldev.Ecommerce_user_service.serviceImpl.UserServiceImpl;
import com.pascaldev.Ecommerce_utils_service.model.PascalDevException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {
	
private final UserServiceImpl userServiceImpl;
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> getUser(@PathVariable("id") Long id) {

		log.debug("Call of get user by id : {}", id);

		try {
			log.trace("Found : {}", "");
			UserDto userDto = userServiceImpl.getById(id);

			return ResponseEntity.status(HttpStatus.OK).body(userDto);
		} catch (PascalDevException e) {
			log.debug(e.getMessage());
			return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
		}
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> getAllUsers(){
		 log.debug("Call of get user  : {}");
			
			try {
				log.trace("Found : {}", "");
				List<UserDto> userDtos = userServiceImpl.getAll();
				return  ResponseEntity.status(HttpStatus.OK).body(userDtos);
			} catch (PascalDevException e) {
				log.debug(e.getMessage());
				return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
			}
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createUser(@RequestBody UserDto userDto){
		
		  log.debug("Call of create user : {}", userDto);
			
			try {
				log.trace("Save : {}", "");
				UserDto newUserDto = userServiceImpl.save(userDto);
				return  ResponseEntity.status(HttpStatus.OK).body(newUserDto);
			} catch (PascalDevException e) {
				log.debug(e.getMessage());
				return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
			}
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto){
		
		 log.debug("Call of update user : {}", id);
			try {
				UserDto newUserDto = userServiceImpl.update(id, userDto);
				return new ResponseEntity<>(newUserDto, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
		 log.debug("Call of delete user : {}", id);
			
			try {
				
				userServiceImpl.deleteById(id);
				return  ResponseEntity.status(HttpStatus.OK).body(true);
			} catch (PascalDevException e) {
				log.debug(e.getMessage());
				return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
			}
	}
	
	@DeleteMapping("/delete-all")
	public ResponseEntity<?> deleteAlls(){
		 log.debug("Call of delete all ");
			try {
				userServiceImpl.deleteAll();
				return  ResponseEntity.status(HttpStatus.OK).body(true);
			}catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}


}

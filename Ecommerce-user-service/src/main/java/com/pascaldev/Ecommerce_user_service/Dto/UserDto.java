package com.pascaldev.Ecommerce_user_service.Dto;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;

import com.pascaldev.Ecommerce_user_service.model.Role;
import com.pascaldev.Ecommerce_user_service.model.User;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE )
public class UserDto {
	
	  Long id;
	  String username;
	  String firstName;
	  String lastName;
	  String password;
	  String email;
	  Role role;
	  boolean enabled;
	  LocalDateTime  createdAt;
	  LocalDateTime updatedAt;
	
	public static UserDto fromUser(User user) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(user, UserDto.class);
	}
	
	public static User fromUserDto(UserDto userDto) {
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(userDto, User.class);
	}

}

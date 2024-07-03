package com.pascaldev.Ecommerce_user_service.Dto;

import org.modelmapper.ModelMapper;

import com.pascaldev.Ecommerce_user_service.model.User;

import lombok.Data;

@Data
public class UserDto {
	
	private Long id;
	private String username;
	private String password;
	private String email;
	
	public static UserDto fromUser(User user) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(user, UserDto.class);
	}
	
	public static User fromUserDto(UserDto userDto) {
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(userDto, User.class);
	}

}

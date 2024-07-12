package com.pascaldev.Ecommerce_user_service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.pascaldev.Ecommerce_user_service.Dto.UserDto;
import com.pascaldev.Ecommerce_user_service.model.User;
import com.pascaldev.Ecommerce_user_service.repository.UserRepository;
import com.pascaldev.Ecommerce_user_service.service.UserService;
import com.pascaldev.Ecommerce_utils_service.model.PascalDevException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService<UserDto> {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
//	private final MessageSource messageSource;
	@Override
	public UserDto getById(Long id) {
		log.trace("try to get user by id  : {}", id);
		
		try {
			Optional<User> user = userRepository.findById(id);
			if (!user.isPresent()) {
				log.trace("this user does not exist");
				return null;
			}
			User newUser = user.get();
			return UserDto.fromUser(newUser);
		}catch (PascalDevException e) {
//			String message = messageSource.getMessage("not found user",new Object[] {user}, locale);
			throw new PascalDevException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "not found user");
		} catch (Exception e) {
			throw e;
		}
		
		
	}

	@Override
	public List<UserDto> getAll() {
		log.trace("try  to find all members");
		Page<User> items=  userRepository.findAll(PageRequest.of(0, 5));
		List<User> userlist= items.getContent();
		if(userlist.isEmpty()) {
			log.trace("this list is empty");
			return null;
		}
		List<UserDto> userDtoList = new ArrayList<>();
		for (User user : userlist) {
			userDtoList.add(UserDto.fromUser(user));	
		}
		return userDtoList;
	}

	@Override
	public UserDto save(UserDto userDto) {
		log.trace("try to save use : {}", userDto);
		
		try {
			if(userDto == null) {
				throw new PascalDevException("unable.save.null.user");
			}
			if(!StringUtils.hasText(userDto.getUsername())) {
				throw new PascalDevException("unable.save.user.with.empty.name");
			}
			Optional<User> user = userRepository.findById(UserDto.fromUserDto(userDto).getId());
			if(user.isPresent()) {
				log.trace("this user already exist");
				return null;
			}
			userDto.setPassword(passwordEncoder.encode(userDto.getPassword())) ;
			User newUser = userRepository.save(UserDto.fromUserDto(userDto));
			return UserDto.fromUser(newUser);
		
		}catch (PascalDevException e) {
			throw e;
		}
		catch (Exception e) {
			log.error("Unexpected error while save user : {}", userDto, e);
			throw new PascalDevException(HttpStatus.INTERNAL_SERVER_ERROR.value(),"unable.to.save.user");
		}
		
	}

	@Override
	public UserDto update(Long id, UserDto userDto) {
		User user = UserDto.fromUserDto(getById(id));
	     if(user == null) {
	    	 throw new PascalDevException("unable.to.update.null.user");
	    	 
	     }
			user.setUsername(userDto.getUsername());
			user.setEmail(userDto.getEmail());
			user.setPassword(userDto.getPassword());
			
		
			UserDto savedUserDto =  save(UserDto.fromUser(user));
	
		return savedUserDto;
	}

	@Override
	public void deleteById(Long id) {
		log.trace("try to delete use by id: {}", id);
		try {
			if(id == null) {
				throw new PascalDevException("unable.to.delete.null.entity");
			}
			userRepository.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public void deleteAll() {
		log.trace("try to delete allUser : {}");
		
		userRepository.deleteAll();
		
	}

}

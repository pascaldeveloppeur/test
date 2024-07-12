package com.pascaldev.Ecommerce_user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pascaldev.Ecommerce_user_service.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User  findByName(String name);
}

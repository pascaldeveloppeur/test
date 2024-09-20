package com.pascaldev.Ecommerce_utils_service.service;

import java.util.List;
/**
 * 
 * @author Pascal Dev
 */

public interface AbstractService <T> {
	
	T getById(Long id);
	List<T> getAll();
	T save(T t);
	T update(Long id, T t);
	void deleteById(Long id);
	void deleteAll();

}

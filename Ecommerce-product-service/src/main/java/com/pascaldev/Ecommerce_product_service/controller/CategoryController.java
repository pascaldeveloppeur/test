package com.pascaldev.Ecommerce_product_service.controller;

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

import com.pascaldev.Ecommerce_product_service.Dto.ProductDto;
import com.pascaldev.Ecommerce_product_service.model.Category;
import com.pascaldev.Ecommerce_product_service.serviceImpl.CategoryServiceImpl;
import com.pascaldev.Ecommerce_product_service.serviceImpl.ProductServiceImpl;
import com.pascaldev.Ecommerce_utils_service.model.PascalDevException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/categories")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryController {

	CategoryServiceImpl categoryServiceImpl;
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> getCategory(@PathVariable("id") Long id) {

		log.debug("Call of get category by id : {}", id);

		try {
			log.trace("Found : {}", "");
			Category category = categoryServiceImpl.getById(id);

			return ResponseEntity.status(HttpStatus.OK).body(category);
		} catch (PascalDevException e) {
			log.debug(e.getMessage());
			return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
		}
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> getAllCategories(){
		 log.debug("Call of get category  : {}");
			
			try {
				log.trace("Found : {}", "");
				List<Category> categorytDtos = categoryServiceImpl.getAll();
				return  ResponseEntity.status(HttpStatus.OK).body(categorytDtos);
			} catch (PascalDevException e) {
				log.debug(e.getMessage());
				return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
			}
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createCategory(@RequestBody Category categoryDto){
		
		  log.debug("Call of create category : {}", categoryDto);
			
			try {
				log.trace("Save : {}", "");
				Category newCategoryDto = categoryServiceImpl.save(categoryDto);
				return  ResponseEntity.status(HttpStatus.OK).body(newCategoryDto);
			} catch (PascalDevException e) {
				log.debug(e.getMessage());
				return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
			}
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateStock(@PathVariable("id") Long id, @RequestBody Category categoryDto){
		
		 log.debug("Call of update category : {}", id);
			try {
				Category newCategoryDto = categoryServiceImpl.update(id, categoryDto);
				return new ResponseEntity<>(newCategoryDto, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
		 log.debug("Call of delete category : {}", id);
			
			try {
				
				categoryServiceImpl.deleteById(id);
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
				categoryServiceImpl.deleteAll();
				return  ResponseEntity.status(HttpStatus.OK).body(true);
			}catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
}

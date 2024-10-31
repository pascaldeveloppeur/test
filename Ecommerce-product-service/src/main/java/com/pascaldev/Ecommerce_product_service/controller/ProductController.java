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
import com.pascaldev.Ecommerce_product_service.model.Price;
import com.pascaldev.Ecommerce_product_service.model.Stock;
import com.pascaldev.Ecommerce_product_service.serviceImpl.ProductServiceImpl;
import com.pascaldev.Ecommerce_utils_service.model.PascalDevException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/products")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductController {
	
	 final ProductServiceImpl productServiceImpl;
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> getProduct(@PathVariable("id") Long id) {

		log.debug("Call of get product by id : {}", id);

		try {
			log.trace("Found : {}", "");
			ProductDto productDto = productServiceImpl.getById(id);

			return ResponseEntity.status(HttpStatus.OK).body(productDto);
		} catch (PascalDevException e) {
			log.debug(e.getMessage());
			return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
		}
	}
	
	@GetMapping("/{id}/stock")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> getStockOfProduct(@PathVariable("id") Long id) {

		log.debug("Call of get stock by productId : {}", id);

		try {
			log.trace("Found : {}", "");
			Stock stock = productServiceImpl.getStockByProductId(id);

			return ResponseEntity.status(HttpStatus.OK).body(stock);
		} catch (PascalDevException e) {
			log.debug(e.getMessage());
			return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
		}
	}
	
	@GetMapping("/{id}/price")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> getPriceOfProduct(@PathVariable("id") Long id) {

		log.debug("Call of get price by productId : {}", id);

		try {
			log.trace("Found : {}", "");
			Price price = productServiceImpl.getPriceByProductId(id);

			return ResponseEntity.status(HttpStatus.OK).body(price);
		} catch (PascalDevException e) {
			log.debug(e.getMessage());
			return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
		}
	}
	
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> getAllProducts(){
		 log.debug("Call of get product  : {}");
			
			try {
				log.trace("Found : {}", "");
				List<ProductDto> productDtos = productServiceImpl.getAll();
				return  ResponseEntity.status(HttpStatus.OK).body(productDtos);
			} catch (PascalDevException e) {
				log.debug(e.getMessage());
				return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
			}
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto){
		
		  log.debug("Call of create product : {}", productDto);
			
			try {
				log.trace("Save : {}", "");
				ProductDto newProductDto = productServiceImpl.save(productDto);
				return  ResponseEntity.status(HttpStatus.OK).body(newProductDto);
			} catch (PascalDevException e) {
				log.debug(e.getMessage());
				return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
			}
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto){
		
		 log.debug("Call of update product : {}", id);
			try {
				ProductDto newProductDto = productServiceImpl.update(id, productDto);
				return new ResponseEntity<>(newProductDto, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	}
	
	@PutMapping("/update/{id}/stock")
	public ResponseEntity<?> updateStockOfProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto){
		
		 log.debug("Call of update stock : {}", id);
			try {
				Stock newProdStock = productServiceImpl.updateStockInProduct(id, productDto);
				return new ResponseEntity<>(newProdStock, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	}
	
	@PutMapping("/update/{id}/price")
	public ResponseEntity<?> updateStockOfPrice(@PathVariable("id") Long id, @RequestBody ProductDto productDto){
		
		 log.debug("Call of update stock : {}", id);
			try {
				Price newProdPrice = productServiceImpl.updatePriceInProduct(id, productDto);
				return new ResponseEntity<>(newProdPrice, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
		 log.debug("Call of delete product : {}", id);
			
			try {
				
				productServiceImpl.deleteById(id);
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
				productServiceImpl.deleteAll();
				return  ResponseEntity.status(HttpStatus.OK).body(true);
			}catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}

}

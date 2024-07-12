package com.pascaldev.Ecommerce_order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RestController
@FeignClient(name = "Ecommerce-product-service")
public interface ProductClient {
	
	@GetMapping("/api/products/{id}")
	Product getProductById(@PathVariable("id") Long id);
	
	@Data
	class Product {
		private Long id;
		private String name;
		private String description;
		private Double price;
		private Integer quantity;
	}

}

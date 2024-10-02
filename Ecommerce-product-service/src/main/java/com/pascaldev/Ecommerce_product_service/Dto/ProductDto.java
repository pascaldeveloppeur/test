package com.pascaldev.Ecommerce_product_service.Dto;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;

import com.pascaldev.Ecommerce_product_service.model.Category;
import com.pascaldev.Ecommerce_product_service.model.Price;
import com.pascaldev.Ecommerce_product_service.model.Product;
import com.pascaldev.Ecommerce_product_service.model.Stock;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {
	
	Long id;
	String name;
	String description;
	String sku;
	Stock stock;
	Price price;
	Category category;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;

	public static ProductDto fromProduct(Product product) {
		ModelMapper modelMapper = new ModelMapper();

		return modelMapper.map(product, ProductDto.class);
	}

	public static Product fromProductDto(ProductDto productDto) {
		ModelMapper modelMapper = new ModelMapper();

		return modelMapper.map(productDto, Product.class);
	}

}

package com.pascaldev.Ecommerce_product_service.Dto;

import org.modelmapper.ModelMapper;

import com.pascaldev.Ecommerce_product_service.model.Product;

import lombok.Data;

@Data
public class ProductDto {
	
	private Long id;
	private String name;
	private String description;
	private Double price;
	private Integer quantity;
	
	public static ProductDto fromProduct(Product product ) {
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(product, ProductDto.class);
	}
	
	public static Product fromProductDto(ProductDto productDto) {
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(productDto, Product.class);
	}

}

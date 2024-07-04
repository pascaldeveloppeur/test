package com.pascaldev.Ecommerce_order_service.Dto;

import org.modelmapper.ModelMapper;

import com.pascaldev.Ecommerce_order_service.model.Order;

import lombok.Data;

@Data
public class OrderDto {
	
	private Long id;
	private Long userId;
	private Double totalAmount;
	
	public static OrderDto fromOrder(Order order) {
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(order, OrderDto.class);
	}
	
	public static Order fromOderDto(OrderDto orderDto) {
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(orderDto, Order.class);
	}
}

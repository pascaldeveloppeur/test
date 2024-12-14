package com.pascaldev.Ecommerce_order_service.Dto;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.pascaldev.Ecommerce_order_service.model.Order;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto {
	
	Long id;
	Long userId;
	Double totalAmount;
	List<Long> productIds;

	public static OrderDto fromOrder(Order order) {
		ModelMapper modelMapper = new ModelMapper();

		return modelMapper.map(order, OrderDto.class);
	}

	public static Order fromOderDto(OrderDto orderDto) {
		ModelMapper modelMapper = new ModelMapper();

		return modelMapper.map(orderDto, Order.class);
	}
}

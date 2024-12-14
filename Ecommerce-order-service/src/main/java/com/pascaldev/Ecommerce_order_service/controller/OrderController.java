package com.pascaldev.Ecommerce_order_service.controller;

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

import com.pascaldev.Ecommerce_order_service.Dto.OrderDto;
import com.pascaldev.Ecommerce_order_service.serviceImpl.OrderServiceImpl;
import com.pascaldev.Ecommerce_utils_service.model.PascalDevException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/orders")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderController {
	
	 final OrderServiceImpl orderServiceImpl;
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> getOrder(@PathVariable("id") Long id) {

		log.debug("Call of get order by id : {}", id);

		try {
			log.trace("Found : {}", "");
			OrderDto orderDto = orderServiceImpl.getById(id);

			return ResponseEntity.status(HttpStatus.OK).body( orderDto);
		} catch (PascalDevException e) {
			log.debug(e.getMessage());
			return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
		}
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> getAllOrders(){
		 log.debug("Call of get order  : {}");
			
			try {
				log.trace("Found : {}", "");
				List<OrderDto> orderDtos = orderServiceImpl.getAll();
				return  ResponseEntity.status(HttpStatus.OK).body(orderDtos);
			} catch (PascalDevException e) {
				log.debug(e.getMessage());
				return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
			}
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createOrder(@RequestBody OrderDto  orderDto){
		
		  log.debug("Call of create order : {}", orderDto);
			
			try {
				log.trace("Save : {}", "");
				OrderDto newOrderDto = orderServiceImpl.save(orderDto);
				return  ResponseEntity.status(HttpStatus.OK).body(newOrderDto);
			} catch (PascalDevException e) {
				log.debug(e.getMessage());
				return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
			}
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateOrder(@PathVariable("id") Long id, @RequestBody OrderDto orderDto){
		
		 log.debug("Call of update order : {}", id);
			try {
				OrderDto newOrderDto = orderServiceImpl.update(id, orderDto);
				return new ResponseEntity<>(newOrderDto, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id){
		 log.debug("Call of delete order : {}", id);
			
			try {
				
				orderServiceImpl.deleteById(id);
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
				orderServiceImpl.deleteAll();
				return  ResponseEntity.status(HttpStatus.OK).body(true);
			}catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}

}

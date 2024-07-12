package com.pascaldev.Ecommerce_order_service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.math.stat.descriptive.summary.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.pascaldev.Ecommerce_order_service.Dto.OrderDto;
import com.pascaldev.Ecommerce_order_service.client.ProductClient;
import com.pascaldev.Ecommerce_order_service.model.Order;
import com.pascaldev.Ecommerce_order_service.repository.OrderRepository;
import com.pascaldev.Ecommerce_order_service.service.OrderService;
import com.pascaldev.Ecommerce_utils_service.model.PascalDevException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
	
	private final OrderRepository orderRepository;
	private final ProductClient productClient;

	@Override
	public OrderDto getById(Long id) {
		log.trace("try to get order by id  : {}", id);

		try {
			Optional<Order> order = orderRepository.findById(id);
			if (!order.isPresent()) {
				log.trace("this order does not exist");
				return null;
			}
			Order newOrder = order.get();
			return OrderDto.fromOrder(newOrder);
		} catch (PascalDevException e) {
//			String message = messageSource.getMessage("not found product",new Object[] {product}, locale);
			throw new PascalDevException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "not found order");
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<OrderDto> getAll() {
		log.trace("try  to find all orders");
		Page<Order> items =  orderRepository.findAll(PageRequest.of(0, 5));
		List<Order> orderList = items.getContent();
		if (orderList.isEmpty()) {
			log.trace("this list is empty");
			return null;
		}
		List<OrderDto> orderDtoList = new ArrayList<>();
		for (Order order : orderList) {
			orderDtoList.add(OrderDto.fromOrder(order));
		}
		return orderDtoList;
	}

	@Override
	public OrderDto save(OrderDto orderDto) {
		log.trace("try to save order : {}", orderDto);

		try {
			if (orderDto == null) {
				throw new PascalDevException("unable.save.null.order");
			}
			if (orderDto.getId().equals(null)) {
				throw new PascalDevException("unable.save.order.with.empty.id");
			}
			
			Optional<Order> order = orderRepository.findById(OrderDto.fromOderDto(orderDto).getId());
			if (order.isPresent()) {
				log.trace("this order already exist");
				return null;
			}
			orderDto.getProductIds().forEach(productId -> {
				ProductClient.Product product = productClient.getProductById(productId);
				if(product.equals(null)) {
					throw new PascalDevException("unable.save.order.with.null.products");
				}
				
			});
			Order newOrder = orderRepository.save(OrderDto.fromOderDto(orderDto));
			return OrderDto.fromOrder(newOrder);

		} catch (PascalDevException e) {
			throw e;
		} catch (Exception e) {
			log.error("Unexpected error while save order : {}", orderDto, e);
			throw new PascalDevException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "unable.to.save.order");
		}
	}

	@Override
	public OrderDto update(Long id, OrderDto orderDto) {
		Order order = OrderDto.fromOderDto(getById(id));
		if (order == null) {
			throw new PascalDevException("unable.to.update.null.order");

		}
		order.setUserId(orderDto.getUserId());
		order.setTotalAmount(orderDto.getTotalAmount());
		OrderDto savedOrderDto = save(OrderDto.fromOrder(order));

		return savedOrderDto;
	}

	@Override
	public void deleteById(Long id) {
		log.trace("try to delete order by id: {}", id);
		try {
			if (id == null) {
				throw new PascalDevException("unable.to.delete.null.entity");
			}
			orderRepository.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public void deleteAll() {
		log.trace("try to delete allOrder : {}");

		orderRepository.deleteAll();
		
	}

}

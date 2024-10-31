package com.pascaldev.Ecommerce_product_service.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.pascaldev.Ecommerce_product_service.Dto.ProductDto;
import com.pascaldev.Ecommerce_product_service.model.Price;
import com.pascaldev.Ecommerce_product_service.model.Product;
import com.pascaldev.Ecommerce_product_service.model.Stock;
import com.pascaldev.Ecommerce_product_service.repository.ProductRepository;
import com.pascaldev.Ecommerce_product_service.service.ProductService;
import com.pascaldev.Ecommerce_utils_service.model.PascalDevException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;

	@Override
	public ProductDto getById(Long id) {
		log.trace("try to get product by id  : {}", id);

		try {
			Optional<Product> product = productRepository.findById(id);
			if (!product.isPresent()) {
				log.trace("this product does not exist");
				return null;
			}
		
			return ProductDto.fromProduct(product.get());
		} catch (PascalDevException e) {
//			String message = messageSource.getMessage("not found product",new Object[] {product}, locale);
			throw new PascalDevException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "not found product");
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<ProductDto> getAll() {
		log.trace("try  to find all products");
		Page<Product> items = productRepository.findAll(PageRequest.of(0, 5));
		List<Product> productList = items.getContent();
		if (productList.isEmpty()) {
			log.trace("this list is empty");
			return null;
		}
		List<ProductDto> productDtoList = new ArrayList<>();
		for (Product product : productList) {
			productDtoList.add(ProductDto.fromProduct(product));
		}
		return productDtoList;
	}

	@Override
	public ProductDto save(ProductDto productDto) {
		log.trace("try to save product : {}", productDto);

		try {
			if (productDto == null) {
				throw new PascalDevException("unable.save.null.product");
			}
			if (!StringUtils.hasText(productDto.getName())) {
				throw new PascalDevException("unable.save.product.with.empty.name");
			}
			Optional<Product> product = productRepository.findById(ProductDto.fromProductDto(productDto).getId());
			if (product.isPresent()) {
				log.trace("this product already exist");
				return ProductDto.fromProduct(product.get());
			}
			Product newProduct = productRepository.save(ProductDto.fromProductDto(productDto));
			return ProductDto.fromProduct(newProduct);

		} catch (PascalDevException e) {
			throw e;
		} catch (Exception e) {
			log.error("Unexpected error while save product : {}", productDto, e);
			throw new PascalDevException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "unable.to.save.product");
		}
	}

	@Override
	public ProductDto update(Long id, ProductDto productDto) {
		Product product = ProductDto.fromProductDto(getById(id));
		if (product == null) {
			throw new PascalDevException("unable.to.update.null.product");

		}
		    product.setName(productDto.getName());
	        product.setDescription(productDto.getDescription());
	        product.setCategory(productDto.getCategory());
	        product.setStock(productDto.getStock());
	        product.setPrice(productDto.getPrice());
	        product.setUpdatedAt(LocalDateTime.now());

		ProductDto savedProductDto = save(ProductDto.fromProduct(product));

		return savedProductDto;
	}

	@Override
	public void deleteById(Long id) {
		log.trace("try to delete product by id: {}", id);
		try {
			if (id == null) {
				throw new PascalDevException("unable.to.delete.null.product");
			}
			productRepository.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void deleteAll() {
		log.trace("try to delete allProduct : {}");

		productRepository.deleteAll();

	}

	@Override
	public Stock getStockByProductId(Long productId) {
		log.trace("try to get stock by productId  : {}", productId);

		try {
			Optional<Product> product = productRepository.findById(productId);
			if (!product.isPresent()) {
				log.trace("this product does not exist");
				return null;
			}
		
			return product.get().getStock();
		} catch (PascalDevException e) {
//			String message = messageSource.getMessage("not found product",new Object[] {product}, locale);
			throw new PascalDevException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "not found product");
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Stock updateStockInProduct(Long productId, ProductDto productDto) {
		Product product = ProductDto.fromProductDto(getById(productId));
		if (product == null) {
			throw new PascalDevException("unable.to.update.null.product");

		}
		   
	        product.setStock(productDto.getStock());
		ProductDto savedProductDto = save(ProductDto.fromProduct(product));

		return savedProductDto.getStock();
	}

	@Override
	public Price getPriceByProductId(Long productId) {
		log.trace("try to get price by productId  : {}", productId);

		try {
			Optional<Product> product = productRepository.findById(productId);
			if (!product.isPresent()) {
				log.trace("this product does not exist");
				return null;
			}
		
			return product.get().getPrice();
		} catch (PascalDevException e) {
//			String message = messageSource.getMessage("not found product",new Object[] {product}, locale);
			throw new PascalDevException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "not found product");
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Price updatePriceInProduct(Long productId,ProductDto productDto) {
		Product product = ProductDto.fromProductDto(getById(productId));
		if (product == null) {
			throw new PascalDevException("unable.to.update.null.product");

		}
		   
	        product.setPrice(productDto.getPrice());
		ProductDto savedProductDto = save(ProductDto.fromProduct(product));

		return savedProductDto.getPrice();
	}

}

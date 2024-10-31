package com.pascaldev.Ecommerce_product_service.service;

import com.pascaldev.Ecommerce_product_service.Dto.ProductDto;
import com.pascaldev.Ecommerce_product_service.model.Price;
import com.pascaldev.Ecommerce_product_service.model.Stock;
import com.pascaldev.Ecommerce_utils_service.service.AbstractService;

public interface ProductService extends AbstractService<ProductDto> {
	
	Stock getStockByProductId(Long productId);
	Stock updateStockInProduct(Long productId, ProductDto productDto);
	Price getPriceByProductId(Long productId);
	Price updatePriceInProduct(Long productId, ProductDto productDto);

}

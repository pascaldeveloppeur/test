package com.pascaldev.Ecommerce_product_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;import com.pascaldev.Ecommerce_product_service.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {

}

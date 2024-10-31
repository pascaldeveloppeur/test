package com.pascaldev.Ecommerce_product_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pascaldev.Ecommerce_product_service.model.Price;

public interface PriceRepository extends JpaRepository<Price, Long> {

}

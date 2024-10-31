package com.pascaldev.Ecommerce_product_service.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@Entity
@Table(name = "stocks")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Stock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stocks_id_seq")
	@SequenceGenerator(name = "stocks_id_seq", sequenceName = "stocks_id_seq", initialValue = 1, allocationSize = 1)
	Long id;

	@NotNull
	int quantity; // Quantité en stock

	LocalDateTime lastUpdated;
}

package com.pascaldev.Ecommerce_product_service.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author Pascal Dev
 */
@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
	@SequenceGenerator(name = "product_seq", sequenceName = "product_seq", initialValue = 1, allocationSize = 1)
	private Long id;
	
	@NotNull(message = "name cannot be null")
	@Column(name = "name")
	private String name;
	
	@NotNull(message = "description cannot be null")
	@Column(name = "description", length = 100)
	private String description;
	 
	@NotNull(message = "price cannot be null")
	@Column(name = "price")
	private Double price;
	
	@NotNull(message = "quantity cannot be null")
	@Column(name = "quantity")
	private Integer quantity;

}

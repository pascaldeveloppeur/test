package com.pascaldev.Ecommerce_order_service.model;

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
@Table(name = "order")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Order implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
	@SequenceGenerator(name = "order_seq", sequenceName = "order_seq", initialValue = 1, allocationSize = 1)
	private Long id;
	
	@NotNull
	@Column(name = "userId")
	private Long userId;
	
	@NotNull
	@Column(name = "totalAmount")
	private Double totalAmount;

}

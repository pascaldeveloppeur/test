package com.pascaldev.Ecommerce_product_service.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "prices")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Price {
	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prices_id_seq")
	@SequenceGenerator(name = "prices_id_seq", sequenceName = "prices_id_seq", initialValue = 1, allocationSize = 1)
    Long id;

    @NotNull
    BigDecimal amount; // Prix actuel

    BigDecimal discount; // Remise éventuelle

    LocalDateTime startDate; // Date de début du prix
    LocalDateTime endDate; 
}

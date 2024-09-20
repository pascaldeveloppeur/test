package com.pascaldev.Ecommerce_user_service.model;

/**
 * @author Pascal Dev
 */

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;



@Entity
@Table(name = "ecommerce-user")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Serializable {
	
	
	 static final long serialVersionUID = -4779073504124416626L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ecommerce-user_seq")
	@SequenceGenerator(name = "ecommerce-user_seq", sequenceName = "ecommerce-user_seq", initialValue = 1, allocationSize = 1)
	 Long id;

	@NotNull(message = "username cannot be null")
	@Column(name = "username", length = 20)
     String username;
	
	@NotNull
	@Column(name = "password",unique = true)
     String password;
    
    @NotNull(message = "email cannot be null")
	@Column(name ="email", unique = true, nullable = false)
     String email;
    
    @NotNull
	@Column(name = "first_name")
    String firstName;
    
    @NotNull
	@Column(name = "last_name")
    String lastName;
  
    @NotNull
	@Enumerated(EnumType.STRING)
	 Role role;
    
    boolean enabled;
    
    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime  createdAt;
    
    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime updatedAt;

}

package com.pascaldev.Ecommerce_utils_service.model;

import org.slf4j.helpers.MessageFormatter;
import org.springframework.http.HttpStatus;

/**
 * @author Pascal Dev
 */
@SuppressWarnings("serial")
public class PascalDevException extends RuntimeException {

	
	private int statusCode;

	public PascalDevException() {
		
		
		this.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
	}
	
    public PascalDevException(String message) {
		
		this(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
	}
    
    public PascalDevException(String message, Object... args) {
		
		this(HttpStatus.INTERNAL_SERVER_ERROR.value(), MessageFormatter.arrayFormat(message, args).getMessage());
	}
	
	

	public PascalDevException(int statutCode, String message) {
		super(message);
		this.setStatusCode(statutCode);
	}
	
	

	

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
		
	}

}

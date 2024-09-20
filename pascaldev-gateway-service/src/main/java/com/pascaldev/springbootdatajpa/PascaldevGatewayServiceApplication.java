package com.pascaldev.springbootdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import lombok.extern.slf4j.Slf4j;


@EnableDiscoveryClient
@SpringBootApplication
@Slf4j
public class PascaldevGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PascaldevGatewayServiceApplication.class, args);
		log.info("Gateway application started");
	}

}

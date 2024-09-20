package com.pascaldev.springbootdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableEurekaServer
@Slf4j
public class PascaldevDescoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PascaldevDescoveryServiceApplication.class, args);
		log.info("Eureka application started");
	}

}

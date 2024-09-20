package com.pascaldev.springbootdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableAdminServer
@EnableDiscoveryClient
@Slf4j
public class PascaldevAdminServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PascaldevAdminServiceApplication.class, args);
		log.info("Administration service started");
	}

}

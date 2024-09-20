package com.pascaldev.springbootdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

import lombok.extern.slf4j.Slf4j;


@EnableConfigServer
@SpringBootApplication
@Slf4j
public class PascaldevConfigurationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PascaldevConfigurationServiceApplication.class, args);
		log.info("Configuration Application Started");
	}

}

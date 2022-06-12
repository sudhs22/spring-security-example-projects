package com.example.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Reference : https://www.baeldung.com/spring-security-basic-authentication
 */
@SpringBootApplication
public class BasicAuthInmemoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicAuthInmemoryApplication.class, args);
	}

}

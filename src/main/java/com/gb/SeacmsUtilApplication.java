package com.gb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SeacmsUtilApplication {
	public static void main(String[] args) {
		SpringApplication.run(SeacmsUtilApplication.class, args);
	}
}

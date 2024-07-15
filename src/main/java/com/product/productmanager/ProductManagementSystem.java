package com.product.productmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@SpringBootApplication
public class ProductManagementSystem {

	public static void main(String[] args) {
		SpringApplication.run(ProductManagementSystem.class, args);

	}

}

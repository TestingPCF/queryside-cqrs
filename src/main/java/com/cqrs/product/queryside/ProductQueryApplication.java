package com.cqrs.product.queryside;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.cqrs.product.queryside.config.RabbitmqConfigProduct;

@SpringBootApplication
@EnableRabbit
@Import(RabbitmqConfigProduct.class)
public class ProductQueryApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ProductQueryApplication.class, args);
	}
	
}

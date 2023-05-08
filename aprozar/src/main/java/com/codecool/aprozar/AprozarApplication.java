package com.codecool.aprozar;

import com.codecool.aprozar.model.Product;
import com.codecool.aprozar.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class AprozarApplication implements CommandLineRunner {
	@Autowired
	ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(AprozarApplication.class, args);
	}

	public void run(String... args) throws Exception {

		List<Product> products=List.of(new Product("Milk"),new Product("Gum"),new Product("Ice"));
		productService.addProducts(products);

	}

}

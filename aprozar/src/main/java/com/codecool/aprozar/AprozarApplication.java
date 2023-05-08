package com.codecool.aprozar;

import aprozar.src.main.java.com.codecool.aprozar.model.Types.ProductType;
import com.codecool.aprozar.model.Product;
import com.codecool.aprozar.model.Seller;
import com.codecool.aprozar.service.ProductService;
import com.codecool.aprozar.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class AprozarApplication implements CommandLineRunner {

	ProductService productService;
	SellerService sellerService;
	@Autowired
	public AprozarApplication(ProductService productService, SellerService sellerService) {
		this.productService = productService;
		this.sellerService=sellerService;
	}

	public static void main(String[] args) {
		SpringApplication.run(AprozarApplication.class, args);
	}

	public void run(String... args) throws Exception {

		Seller seller1=new Seller();
		seller1.setName("Mircea");
		sellerService.addSeller(seller1);

	Product milk =new Product();
	milk.setName("milk");
	milk.setProductType(ProductType.Dairy);
	milk.setSeller(seller1);
	seller1.setAvailableProducts(List.of(milk));
	productService.addProduct(milk);

	}

}

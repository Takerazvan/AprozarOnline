package com.codecool.backend;


import com.codecool.backend.model.Produce.Product;
import com.codecool.backend.model.Produce.Types.ProductType;
import com.codecool.backend.model.Users.Seller;
import com.codecool.backend.service.ProductService;
import com.codecool.backend.service.SellerService;
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
        this.sellerService = sellerService;
    }

    public static void main(String[] args) {
        SpringApplication.run(AprozarApplication.class, args);
    }


    public void run(String... args) {

        Seller seller1 = new Seller();
        seller1.setName("Mircea");
        sellerService.addSeller(seller1);

        Product milk = new Product();
        milk.setName("Milk");
        milk.setProductType(ProductType.Dairy);
        milk.setSeller(seller1);
        seller1.setAvailableProducts(List.of(milk));
        productService.addProduct(milk);


        Seller seller2 = new Seller();
        seller2.setName("Mihai");
        sellerService.addSeller(seller2);

        Product tomato = new Product();
        tomato.setName("Tomatoes");
        tomato.setProductType(ProductType.Vegetables);
        tomato.setSeller(seller2);
        seller2.setAvailableProducts(List.of(tomato));
        productService.addProduct(tomato);

    }

}
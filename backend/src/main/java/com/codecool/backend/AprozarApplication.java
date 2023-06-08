package com.codecool.backend;


import com.codecool.backend.model.products.Product;
import com.codecool.backend.model.products.ProductService;
import com.codecool.backend.model.products.Types.ProductType;
import com.codecool.backend.model.users.RegistrationRequest;
import com.codecool.backend.security.auth.AuthenthicationService;
import com.codecool.backend.service.CustomerService;
import com.codecool.backend.model.products.ProductDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AprozarApplication implements CommandLineRunner {

    @Autowired
    private AuthenthicationService authenthicationService;
    @Autowired
    private ProductService productService;


    public static void main(String[] args) {
        SpringApplication.run(AprozarApplication.class, args);
    }


    public void run(String... args) {
        List<Product> productList = new ArrayList<>();

        // Example products
        Product bread = Product.builder()
                .name("Bread")
                .productType(ProductType.Meat)
                .price(5.00)
                .build();
        productList.add(bread);

        Product milk = Product.builder()
                .name("Milk")
                .productType(ProductType.Dairy)
                .price(3.50)
                .quantity(10)
                .build();
        productList.add(milk);

        Product apple = Product.builder()
                .name("Apple")
                .productType(ProductType.Fruits)
                .price(1.25)
                .quantity(15)
                .build();
        productList.add(apple);


        RegistrationRequest newUserRequest=new RegistrationRequest("admin","admin","mail","123");
        authenthicationService.registerCustomer(newUserRequest);
        productService.addInventory(productList);



    }

}

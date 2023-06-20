package com.codecool.backend;


import com.codecool.backend.products.Product;
import com.codecool.backend.products.ProductService;
import com.codecool.backend.products.Types.ProductType;
import com.codecool.backend.s3.S3Service;
import com.codecool.backend.security.auth.AuthenticationService;
import com.codecool.backend.users.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AprozarApplication implements CommandLineRunner {

    @Autowired
    private AuthenticationService authenthicationService;
    @Autowired
    private ProductService productService;
    @Autowired
    private S3Service s3Service;


    public static void main(String[] args) {
        SpringApplication.run(AprozarApplication.class, args);
    }


    public void run(String... args) {
        List<Product> productList = new ArrayList<>();


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


        RegistrationRequest newUserRequest = new RegistrationRequest("admin", "admin", "mail@yahoo.com", "123","SELLER");
        authenthicationService.registerCustomer(newUserRequest);
        productService.addProducts(productList);
//        s3Service.putObject("aprozar", "key", apple.getName().getBytes());
//
//        byte[] obj = s3Service.getObject("aprozar", "key");
//        System.out.println("Heep-heep horay" + new String(obj));
    }

}

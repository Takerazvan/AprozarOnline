package com.codecool.backend;


import com.codecool.backend.fileStorage.ImageService;
import com.codecool.backend.products.Product;
import com.codecool.backend.products.ProductService;
import com.codecool.backend.products.Types.ProductType;
import com.codecool.backend.security.auth.AuthenticationService;
import com.codecool.backend.security.auth.LoginRequest;
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



    public static void main(String[] args) {
        SpringApplication.run(AprozarApplication.class, args);
    }


    public void run(String... args) {


        RegistrationRequest newUserRequest = new RegistrationRequest("admin", "admin", "simam9520@gmail.com", "123","SELLER");
        authenthicationService.registerCustomer(newUserRequest);

    }

}

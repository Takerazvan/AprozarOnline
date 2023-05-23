package com.codecool.backend;


import com.codecool.backend.model.users.AppUserService;
import com.codecool.backend.security.RegistrationRequest;
import com.codecool.backend.service.CustomerService;
import com.codecool.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class AprozarApplication implements CommandLineRunner {

    @Autowired
    private CustomerService customerService;


    public static void main(String[] args) {
        SpringApplication.run(AprozarApplication.class, args);
    }


    public void run(String... args) {
        RegistrationRequest newUserRequest=new RegistrationRequest("admin","admin","admin@email.com","pass");
        customerService.register(newUserRequest);


    }

}

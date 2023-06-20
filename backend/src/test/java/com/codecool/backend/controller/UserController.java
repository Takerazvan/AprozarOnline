package com.codecool.backend.controller;

import com.codecool.backend.users.RegistrationRequest;
import com.codecool.backend.users.repository.AppUserRole;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Random;
import java.util.UUID;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class UserController {
    @Autowired
    private WebTestClient webTestClient;

    private static final Random RANDOM = new Random();
    private static final String CUSTOMER_PATH = "/api/v1/customers";

    Faker faker = new Faker();
    Name fakerName = faker.name();
    String firstName=fakerName.firstName();
    String lastName=fakerName.lastName();
    int randomNumber=RANDOM.nextInt(1,99);
    String email=fakerName.lastName()+ UUID.randomUUID()+"@email.com";
    String password="1234";
    AppUserRole role=randomNumber%2==0?AppUserRole.BUYER:AppUserRole.SELLER;

    RegistrationRequest registrationRequest = new RegistrationRequest(
            firstName,lastName,email,password,role.name()
    );

    String jwtToken = webTestClient.post()
            .uri("api/auth/register")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(registrationRequest), RegistrationRequest.class)
            .exchange()
            .expectStatus()
            .isOk()
            .returnResult(Void.class)
            .getResponseHeaders()
            .get(AUTHORIZATION)
            .get(0);

}

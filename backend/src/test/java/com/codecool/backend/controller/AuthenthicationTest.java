package com.codecool.backend.controller;

import com.codecool.backend.security.auth.AuthenticationResponse;
import com.codecool.backend.security.auth.LoginRequest;
import com.codecool.backend.security.jwt.JWTService;
import com.codecool.backend.users.RegistrationRequest;
import com.codecool.backend.users.repository.AppUserDTO;
import com.codecool.backend.users.repository.AppUserRole;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Random;
import java.util.UUID;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.testcontainers.shaded.org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class AuthenthicationTest {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private JWTService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Random RANDOM=new Random();
    private static final String AUTH="/api/auth";

    @Test
    void canLogin(){
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

        LoginRequest loginRequest=new LoginRequest(email,password);

        System.out.println(registrationRequest);
        webTestClient.post()
                .uri(AUTH + "/register")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(registrationRequest), RegistrationRequest.class)
                .exchange()
                .expectStatus()
                .isOk();
        System.out.println(loginRequest);
        EntityExchangeResult<AuthenticationResponse> result = webTestClient.post()
                .uri(AUTH + "/login")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(loginRequest),LoginRequest.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(new ParameterizedTypeReference<AuthenticationResponse>() {
                })
                .returnResult();
        String jwtToken = result.getResponseHeaders()
                .get(HttpHeaders.AUTHORIZATION)
                .get(0);

        AuthenticationResponse authenticationResponse = result.getResponseBody();
        System.out.println(authenticationResponse);
        AppUserDTO userDTO=authenticationResponse.appUserDTO();

        Assertions.assertThat(jwtService.isTokenValid(
                jwtToken,
                userDTO.email())).isTrue();


        Assertions.assertThat(userDTO.email()).isEqualTo(email);
        Assertions.assertThat(userDTO.firstName()).isEqualTo(firstName);
        Assertions.assertThat(userDTO.lastName()).isEqualTo(lastName);
        Assertions.assertThat(passwordEncoder.matches(password, userDTO.password()));
        Assertions.assertThat(userDTO.role()).isEqualTo(role);
    }
}

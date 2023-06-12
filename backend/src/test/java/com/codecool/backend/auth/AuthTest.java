package com.codecool.backend.auth;

import com.codecool.backend.security.auth.AuthenticationResponse;
import com.codecool.backend.security.auth.LoginRequest;
import com.codecool.backend.security.jwt.JWTService;
import com.codecool.backend.users.AppUserDTO;
import com.codecool.backend.users.RegistrationRequest;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class AuthTest {
    private static final Random RANDOM = new Random();
    private static final String AUTHENTICATION_PATH = "/api/auth";
    private static final String CUSTOMER_PATH = "/api/v1/customers";
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private JWTService jwtUtil;

    @Test
    void canLogin() {

        Faker faker = new Faker();
        Name fakerName = faker.name();

        String firstName = fakerName.firstName();
        String lastName = fakerName.lastName();
        String email = fakerName.lastName() + "-" + UUID.randomUUID() + "@gmail.com";


        String password = "password";

        RegistrationRequest customerRegistrationRequest = new RegistrationRequest(
                firstName, lastName, email, password
        );

        LoginRequest authenticationRequest = new LoginRequest(
                email,
                password
        );

        webTestClient.post()
                .uri(AUTHENTICATION_PATH + "/login")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(authenticationRequest), AuthenticationRequest.class)
                .exchange()
                .expectStatus()
                .isUnauthorized();


        webTestClient.post()
                .uri(AUTHENTICATION_PATH + "/register")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        Mono.just(customerRegistrationRequest),
                        RegistrationRequest.class
                )
                .exchange()
                .expectStatus()
                .isOk();

        EntityExchangeResult<AuthenticationResponse> result = webTestClient.post()
                .uri(AUTHENTICATION_PATH + "/login")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(authenticationRequest), AuthenticationRequest.class)
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

        AppUserDTO customerDTO = authenticationResponse.appUserDTO();

        assertThat(jwtUtil.isTokenValid(
                jwtToken,
                customerDTO.email())).isTrue();

        assertThat(customerDTO.email()).isEqualTo(email);
        assertThat(customerDTO.firstName()).isEqualTo(firstName);
        assertThat(customerDTO.lastName()).isEqualTo(lastName);
        assertThat(customerDTO.password()).isEqualTo(password);


    }
}

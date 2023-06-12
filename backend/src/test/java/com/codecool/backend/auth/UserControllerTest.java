package com.codecool.backend.auth;

import com.codecool.backend.users.AppUserDTO;
import com.codecool.backend.users.AppUserRole;
import com.codecool.backend.users.RegistrationRequest;
import com.codecool.backend.users.UpdateRequest;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class UserControllerTest {
    private static final Random RANDOM = new Random();
    private static final String AUTH_PATH = "/api/auth/register";
    private static final String CUSTOMER_PATH = "api/customers";
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void canRegisterCustomer() {

        Faker faker = new Faker();
        Name fakerName = faker.name();

        String firstName = fakerName.firstName();
        String lastName = fakerName.lastName();
        String email = fakerName.lastName() + "-" + UUID.randomUUID() + "@gmail.com";


        String password = "password";

        RegistrationRequest userRegistrationRequest = new RegistrationRequest(
                firstName, lastName, email, password
        );

        String jwtToken = webTestClient.post()
                .uri(AUTH_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(userRegistrationRequest), RegistrationRequest.class)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(Void.class)
                .getResponseHeaders()
                .get(AUTHORIZATION)
                .get(0);


        List<AppUserDTO> allCustomers = webTestClient.get()
                .uri(CUSTOMER_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, String.format("Bearer %s", jwtToken))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<AppUserDTO>() {
                })
                .returnResult()
                .getResponseBody();

        Long id = allCustomers.stream()
                .filter(customer -> customer.email().equals(email))
                .map(AppUserDTO::id)
                .findFirst()
                .orElseThrow();


        AppUserDTO expectedCustomer = new AppUserDTO(
                id,
                firstName,
                lastName,
                email,
                password,
                AppUserRole.BUYER
        );

        assertThat(allCustomers).contains(expectedCustomer);


        webTestClient.get()
                .uri(CUSTOMER_PATH + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, String.format("Bearer %s", jwtToken))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(new ParameterizedTypeReference<AppUserDTO>() {
                })
                .isEqualTo(expectedCustomer);
    }

    @Test
    void canDeleteCustomer() {
        Faker faker = new Faker();
        Name fakerName = faker.name();

        String firstName = fakerName.firstName();
        String lastName = fakerName.lastName();
        String email = fakerName.lastName() + "-" + UUID.randomUUID() + "@gmail.com";


        String password = "password";

        RegistrationRequest request = new RegistrationRequest(
                firstName, lastName, email, password
        );

        RegistrationRequest request2 = new RegistrationRequest(
                firstName, lastName, email + "2", password
        );

        // send a post request to create customer 1
        webTestClient.post()
                .uri(CUSTOMER_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), RegistrationRequest.class)
                .exchange()
                .expectStatus()
                .isOk();

        // send a post request to create customer 2
        String jwtToken = webTestClient.post()
                .uri(CUSTOMER_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request2), RegistrationRequest.class)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(Void.class)
                .getResponseHeaders()
                .get(AUTHORIZATION)
                .get(0);

        // get all customers
        List<AppUserDTO> allCustomers = webTestClient.get()
                .uri(CUSTOMER_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, String.format("Bearer %s", jwtToken))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<AppUserDTO>() {
                })
                .returnResult()
                .getResponseBody();


        Long id = allCustomers.stream()
                .filter(customer -> customer.email().equals(email))
                .map(AppUserDTO::id)
                .findFirst()
                .orElseThrow();

        // customer 2 deletes customer 1
        webTestClient.delete()
                .uri(CUSTOMER_PATH + "/{id}", id)
                .header(AUTHORIZATION, String.format("Bearer %s", jwtToken))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk();

        // customer 2 gets customer 1 by id
        webTestClient.get()
                .uri(CUSTOMER_PATH + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, String.format("Bearer %s", jwtToken))
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    void canUpdateCustomer() {
        Faker faker = new Faker();
        Name fakerName = faker.name();

        String firstName = fakerName.firstName();
        String lastName = fakerName.lastName();
        String email = fakerName.lastName() + "-" + UUID.randomUUID() + "@gmail.com";


        String password = "password";

        RegistrationRequest request = new RegistrationRequest(
                firstName, lastName, email, password
        );
        // send a post request
        String jwtToken = webTestClient.post()
                .uri(CUSTOMER_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), RegistrationRequest.class)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(Void.class)
                .getResponseHeaders()
                .get(AUTHORIZATION)
                .get(0);

        // get all customers
        List<AppUserDTO> allCustomers = webTestClient.get()
                .uri(CUSTOMER_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, String.format("Bearer %s", jwtToken))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<AppUserDTO>() {
                })
                .returnResult()
                .getResponseBody();


        Long id = allCustomers.stream()
                .filter(customer -> customer.email().equals(email))
                .map(AppUserDTO::id)
                .findFirst()
                .orElseThrow();

        // update customer

        String newName = "Ali";

        UpdateRequest updateRequest = new UpdateRequest(
                newName, null, null, null
        );

        webTestClient.put()
                .uri(CUSTOMER_PATH + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, String.format("Bearer %s", jwtToken))
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(updateRequest), UpdateRequest.class)
                .exchange()
                .expectStatus()
                .isOk();

        // get customer by id
        AppUserDTO updatedCustomer = webTestClient.get()
                .uri(CUSTOMER_PATH + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, String.format("Bearer %s", jwtToken))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(AppUserDTO.class)
                .returnResult()
                .getResponseBody();

        AppUserDTO expected = new AppUserDTO(
                id,
                newName,
                lastName,
                email,
                password,
                AppUserRole.BUYER

        );

        assertThat(updatedCustomer).isEqualTo(expected);
    }


}

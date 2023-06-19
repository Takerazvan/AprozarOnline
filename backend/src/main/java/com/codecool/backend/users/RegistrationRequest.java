package com.codecool.backend.users;

public record RegistrationRequest(
         String firstName,
         String lastName,
         String email,
         String password,
         String role
) {

}
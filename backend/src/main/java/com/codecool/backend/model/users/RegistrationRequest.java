package com.codecool.backend.model.users;

import lombok.AllArgsConstructor;
import lombok.Data;


public record RegistrationRequest(
         String firstName,
         String lastName,
         String email,
         String password
) {

}
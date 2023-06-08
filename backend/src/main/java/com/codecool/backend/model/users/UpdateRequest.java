package com.codecool.backend.model.users;

public record UpdateRequest(
        String firstname,

        String lastname,

        String email,

        String address

) {
}

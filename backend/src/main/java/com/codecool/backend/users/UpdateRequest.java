package com.codecool.backend.users;

public record UpdateRequest(
        String firstname,

        String lastname,

        String email,

        String password,

        String address

) {
}

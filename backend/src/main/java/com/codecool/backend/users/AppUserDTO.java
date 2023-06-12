package com.codecool.backend.users;

public record AppUserDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String password,
        AppUserRole role
) {
}

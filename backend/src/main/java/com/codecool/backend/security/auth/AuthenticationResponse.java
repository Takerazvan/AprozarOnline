package com.codecool.backend.security.auth;

import com.codecool.backend.users.repository.AppUserDTO;

public record AuthenticationResponse(
        String token,
        AppUserDTO appUserDTO) {
}
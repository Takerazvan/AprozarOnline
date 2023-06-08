package com.codecool.backend.security.auth;

import com.codecool.backend.model.users.AppUserDTO;

public record AuthenthicationResponse(
    String token,
    AppUserDTO appUserDTO){
}

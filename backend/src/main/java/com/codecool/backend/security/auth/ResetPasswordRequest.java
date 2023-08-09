package com.codecool.backend.security.auth;

public record ResetPasswordRequest(String email, String newPassword) {

}
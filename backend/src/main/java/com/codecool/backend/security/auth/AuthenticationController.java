package com.codecool.backend.security.auth;

import com.codecool.backend.exception.ResourceNotFoundException;
import com.codecool.backend.notifications.EmailService;
import com.codecool.backend.security.jwt.JWTService;
import com.codecool.backend.users.RegistrationRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@AllArgsConstructor
@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final EmailService emailService;
    private final JWTService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        AuthenticationResponse response = authenticationService.login(request);

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, response.token())
                .body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request) {
        AuthenticationResponse response = authenticationService.registerCustomer(request);
        return ResponseEntity.ok()
                .body(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        authenticationService.logout(request);
        return ResponseEntity.ok("Logged out successfully");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> reset(@RequestBody String email) {

        try {
            // Generate the reset password token with 10 minutes expiration
            String resetToken = authenticationService.generateResetPasswordToken(email);

            // Create the reset password link with the generated token

            String resetLink = "http://localhost:8080/api/auth/reset-pass?token=" + resetToken;

            // Send the reset password email
            LocalDateTime expirationTime = LocalDateTime.now().plus(10, ChronoUnit.MINUTES);
            emailService.sendPasswordResetEmail(email, resetLink, expirationTime);

            return ResponseEntity.ok("Email sent");
        } catch (IllegalArgumentException e) {
            // Handle the case where the user does not exist
            throw new ResourceNotFoundException("User does not exist");
        }
    }

    @GetMapping("/user")
    public RedirectView verify(@RequestParam("token") String token) {
        boolean emailVerified = authenticationService.verifyEmail(token);

        if (emailVerified) {
            // Redirect to the login page
            return new RedirectView("http://127.0.0.1:3000/login");
        } else {
            // Redirect to an error page
            return new RedirectView("http://127.0.0.1:3000/error");
        }
    }

    @GetMapping("/reset-pass")
    public RedirectView resetForm(@RequestParam("token") String token) {


        boolean isExpired = authenticationService.isResetTokenExpired(token);
        if (!isExpired) {
            return new RedirectView("http://127.0.0.1:3000/reset-password-form?token=" + token);
        } else {
            return new RedirectView("http://127.0.0.1:3000/reset-password");

        }

    }


}

package com.codecool.backend.security.auth;

import com.codecool.backend.users.RegistrationRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@AllArgsConstructor
@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        AuthenticationResponse response = authenticationService.login(request);

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, response.token())
                .body(response);
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request){
        AuthenticationResponse response = authenticationService.registerCustomer(request);
        return ResponseEntity.ok()
                .body(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        authenticationService.logout(request);
        return ResponseEntity.ok("Logged out successfully");
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
}

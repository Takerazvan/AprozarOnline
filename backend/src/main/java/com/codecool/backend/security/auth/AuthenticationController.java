package com.codecool.backend.security.auth;

import com.codecool.backend.model.users.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

private final AuthenthicationService authenthicationService;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        AuthenthicationResponse response = authenthicationService.login(request);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, response.token())
                .body(response);
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request){
        AuthenthicationResponse response=authenthicationService.registerCustomer(request);
        return ResponseEntity.ok()
                .body(response);
    }
}

package com.codecool.backend.security.auth;

import com.codecool.backend.notifications.EmailService;
import com.codecool.backend.security.jwt.JWTService;
import com.codecool.backend.users.repository.AppUser;
import com.codecool.backend.users.repository.AppUserDTO;
import com.codecool.backend.users.repository.AppUserDTOMapper;
import com.codecool.backend.users.RegistrationRequest;
import com.codecool.backend.users.service.AppUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final AppUserDTOMapper appUserDTOMapper;
    private final AppUserService userService;
    private EmailService emailService;

    public AuthenticationService(AuthenticationManager authenticationManager, JWTService jwtService, AppUserDTOMapper appUserDTOMapper, @Qualifier("appUser") AppUserService userService,EmailService emailService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.appUserDTOMapper = appUserDTOMapper;
        this.userService = userService;
        this.emailService=emailService;
    }

    public AuthenticationResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        AppUser principal = (AppUser) authentication.getPrincipal();
        AppUserDTO userDTO = appUserDTOMapper.apply(principal);
        String token = jwtService.issueToken(userDTO.email());
        System.out.println(token);
        return new AuthenticationResponse(token, userDTO);
    }

    public AuthenticationResponse registerCustomer(RegistrationRequest request) {
        AppUser newUser = userService.addUser(request);

        AppUserDTO newUserDTO = appUserDTOMapper.apply(newUser);


        String token = jwtService.issueToken(newUserDTO.email());
        String email = newUser.getEmail();
        String message = "Hello"+ " " + newUser.getFirstName() + " "+ "Welcome to Aprozar Online ! Thank you for registering.";
        emailService.send(email, message);
        return new AuthenticationResponse(token, newUserDTO);
    }

    public void logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            String subject = jwtService.getSubject(jwt);
            if (subject != null) {
                SecurityContextHolder.getContext().setAuthentication(null);
            }
        }
    }

}

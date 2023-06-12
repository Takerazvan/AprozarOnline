package com.codecool.backend.security.auth;

import com.codecool.backend.security.jwt.JWTService;
import com.codecool.backend.service.CustomerService;
import com.codecool.backend.users.AppUser;
import com.codecool.backend.users.AppUserDTO;
import com.codecool.backend.users.AppUserDTOMapper;
import com.codecool.backend.users.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final AppUserDTOMapper appUserDTOMapper;
    private final CustomerService customerService;

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
        AppUser newUser = customerService.registerCustomer(request);

        AppUserDTO newUserDTO = appUserDTOMapper.apply(newUser);

        String token = jwtService.issueToken(newUserDTO.email());

        return new AuthenticationResponse(token, newUserDTO);
    }



}

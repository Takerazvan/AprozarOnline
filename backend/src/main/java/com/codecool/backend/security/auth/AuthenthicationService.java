package com.codecool.backend.security.auth;

import com.codecool.backend.model.users.AppUser;
import com.codecool.backend.model.users.AppUserDTO;
import com.codecool.backend.model.users.AppUserDTOMapper;
import com.codecool.backend.model.users.RegistrationRequest;
import com.codecool.backend.security.jwt.JWTService;
import com.codecool.backend.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class AuthenthicationService {
    private final AuthenticationManager authenticationManager;
private final JWTService jwtService;
private  final AppUserDTOMapper appUserDTOMapper;
private final CustomerService customerService;

    public AuthenthicationResponse login(LoginRequest request) {
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
        return new AuthenthicationResponse(token, userDTO);
    }

    public AuthenthicationResponse registerCustomer(RegistrationRequest request){
      AppUser newUser=customerService.registerCustomer(request);

      AppUserDTO newUserDTO=appUserDTOMapper.apply(newUser);

      String token=jwtService.issueToken(newUserDTO.email());
        //System.out.println("Tokeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeennnnnnnnnnnnnnnnnnnnnn!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+token);
      return new AuthenthicationResponse(token,newUserDTO);
    }



}

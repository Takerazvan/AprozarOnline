package com.codecool.backend.service;

import com.codecool.backend.email.EmailSender;
import com.codecool.backend.model.users.AppUser;
import com.codecool.backend.repository.AppUserRepository;
import com.codecool.backend.security.token.Token;
import com.codecool.backend.security.token.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenService tokenService;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findAppUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public void signUpUser(AppUser appUser) {
        boolean userExists = appUserRepository.findAppUserByEmail(appUser.getEmail()).isPresent();
        if (userExists) {
            throw new IllegalStateException("Email already taken");
        }


        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);
        Token token = tokenService.generateToken(appUser);
        appUser.setToken(token);
    }

    public void logInUser(String email, String password) {
        AppUser user = appUserRepository.findAppUserByEmail(email).orElseThrow(() -> new IllegalStateException("Email-password combination is not a match"));

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new IllegalStateException("Email-password combination is not a match");
        }

       Token newToken= tokenService.generateToken(user);
        user.setToken(newToken);
    }

    public AppUser findAppUserById(Long id) {
        return appUserRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    public void deleteUser(Long id) {
        appUserRepository.deleteById(id);
    }

    public AppUser updateAppUser(Long id, AppUser updatedUser) {
        AppUser existingUser = appUserRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));


        return appUserRepository.save(existingUser);
    }
}



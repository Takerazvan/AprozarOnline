package com.codecool.backend.service;
import com.codecool.backend.email.EmailSender;
import com.codecool.backend.model.products.ShoppingCart;
import com.codecool.backend.model.products.ShoppingCartRepository;
import com.codecool.backend.model.users.AppUser;
import com.codecool.backend.model.users.AppUserRole;
import com.codecool.backend.repository.AppUserRepository;
import com.codecool.backend.security.RegistrationRequest;
import com.codecool.backend.security.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CustomerService extends AppUserService {
     @Autowired
    public CustomerService(AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder, TokenService tokenService, ShoppingCartRepository shoppingCartRepository) {
        super(appUserRepository, bCryptPasswordEncoder, tokenService);
        this.shoppingCartRepository = shoppingCartRepository;
    }

    private final ShoppingCartRepository shoppingCartRepository;


    public void register(RegistrationRequest request) {
        var newUser = AppUser.builder().firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .appUserRole(AppUserRole.BUYER)
                .password(request.getPassword())
                .build();

        shoppingCartRepository.save(new ShoppingCart(newUser.getId()));


        signUpUser(newUser);

    }




public ShoppingCart getCart(Long id){
        return shoppingCartRepository.findByUserId(id).orElseThrow(() -> new NoSuchElementException("User not found"));
}




}

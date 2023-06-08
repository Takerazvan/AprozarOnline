package com.codecool.backend.service;

import com.codecool.backend.model.products.ShoppingCart;
import com.codecool.backend.model.products.ShoppingCartRepository;
import com.codecool.backend.model.users.*;
import com.codecool.backend.security.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CustomerService extends AppUserService {


    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public CustomerService(AppUserDao appUserDao, AppUserDTOMapper userDTOMapper, PasswordEncoder passwordEncoder, ShoppingCartRepository shoppingCartRepository) {
        super(appUserDao, userDTOMapper, passwordEncoder);
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public AppUser registerCustomer(RegistrationRequest request) {

      AppUser newUser=addUser(request,AppUserRole.BUYER);
        shoppingCartRepository.save(new ShoppingCart(newUser.getId()));

        return newUser;
    }


    public ShoppingCart getCart(Long id) {
        return shoppingCartRepository.findByUserId(id).orElseThrow(() -> new NoSuchElementException("User not found"));
    }


}

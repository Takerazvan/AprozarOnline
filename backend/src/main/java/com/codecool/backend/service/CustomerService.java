package com.codecool.backend.service;

import com.codecool.backend.products.ShoppingCart;
import com.codecool.backend.products.ShoppingCartRepository;
import com.codecool.backend.s3.S3Buckets;
import com.codecool.backend.s3.S3Service;
import com.codecool.backend.users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CustomerService extends AppUserService {


    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public CustomerService(@Qualifier("jdbc") AppUserDao appUserDao, AppUserDTOMapper userDTOMapper, PasswordEncoder passwordEncoder, S3Service s3Service, S3Buckets s3Buckets, ShoppingCartRepository shoppingCartRepository) {
        super(appUserDao, userDTOMapper, passwordEncoder, s3Service, s3Buckets);
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public AppUser registerCustomer(RegistrationRequest request) {

        AppUser newUser = addUser(request, AppUserRole.BUYER);
        shoppingCartRepository.save(new ShoppingCart(newUser.getId()));

        return newUser;
    }


    public ShoppingCart getCart(Long id) {
        return shoppingCartRepository.findByUserId(id).orElseThrow(() -> new NoSuchElementException("User not found"));
    }


}

package com.codecool.backend.service;
import com.codecool.backend.model.products.ShoppingCart;
import com.codecool.backend.model.products.ShoppingCartRepository;
import com.codecool.backend.model.users.AppUser;
import com.codecool.backend.model.users.AppUserRepository;
import com.codecool.backend.model.users.AppUserRole;
import com.codecool.backend.model.users.AppUserService;
import com.codecool.backend.security.RegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class CustomerService {

    private AppUserService appUserService;
    private ShoppingCartRepository shoppingCartRepository;


    public void register(RegistrationRequest request) {
        var newUser = AppUser.builder().firstname(request.getFirstName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .role(AppUserRole.BUYER)
                .password(request.getPassword())
                .build();

        shoppingCartRepository.save(new ShoppingCart(newUser.getId()));

        appUserService.signUpUser(newUser);

    }
}

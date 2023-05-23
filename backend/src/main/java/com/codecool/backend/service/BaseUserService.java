//package com.codecool.backend.service;
//
//import com.codecool.backend.model.products.ShoppingCart;
//import com.codecool.backend.model.users.AppUser;
//import com.codecool.backend.model.users.AppUserRepository;
//import com.codecool.backend.model.users.AppUserRole;
//import com.codecool.backend.model.users.AppUserService;
//import com.codecool.backend.security.RegistrationRequest;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.NoSuchElementException;
//@AllArgsConstructor
//@Service
//public class BaseUserService {
//    private AppUserService appUserService;
//
//
//    private void register(RegistrationRequest request){
//     var newUser=AppUser.builder().firstname(request.getFirstName())
//                     .lastname(request.getLastName())
//                             .email(request.getEmail())
//                                     .role(AppUserRole.BUYER)
//                                             .password(request.getPassword())
//                                                     .shoppingCart(new ShoppingCart())
//             .build();
//
//     newUser.getShoppingCart().setCustomer(newUser);
//
//        appUserService.signUpUser(newUser);
// }
//}

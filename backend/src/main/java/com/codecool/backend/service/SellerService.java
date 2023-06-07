package com.codecool.backend.service;

import com.codecool.backend.email.EmailSender;
import com.codecool.backend.model.products.Product;
import com.codecool.backend.model.products.ProductForm;
import com.codecool.backend.model.users.AppUser;
import com.codecool.backend.model.users.AppUserRole;
import com.codecool.backend.repository.AppUserRepository;
import com.codecool.backend.security.RegistrationRequest;
import com.codecool.backend.security.token.TokenService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class SellerService extends AppUserService{
    private final ProductService productService;
    @Autowired
    public SellerService(AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder, TokenService tokenService, ProductService productService) {
        super(appUserRepository, bCryptPasswordEncoder, tokenService);
        this.productService = productService;

    }

    public List<Product> getProductList(Long id){
      return  productService.getAllProductsBySeller(id);
    }


    public void register(RegistrationRequest request){
        var newUser = AppUser.builder().firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .appUserRole(AppUserRole.SELLER)
                .password(request.getPassword())
                .build();

        signUpUser(newUser);
    }

    public void addProduct(ProductForm productForm,Long userId){
        var product=Product.builder().productType(productForm.getType())
                .name(productForm.getName())
                .quantity(productForm.getQuantity())
                .price(productForm.getPrice())
                .userId(userId).build();

        productService.addProduct(product);

    }





}




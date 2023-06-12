package com.codecool.backend.service;

import com.codecool.backend.products.Product;
import com.codecool.backend.products.ProductForm;
import com.codecool.backend.products.ProductService;
import com.codecool.backend.s3.S3Buckets;
import com.codecool.backend.s3.S3Service;
import com.codecool.backend.users.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SellerService extends AppUserService {
    private final ProductService productService;

    public SellerService(@Qualifier("jdbc") AppUserDao appUserDao, AppUserDTOMapper userDTOMapper, PasswordEncoder passwordEncoder, S3Service s3Service, S3Buckets s3Buckets, ProductService productService) {
        super(appUserDao, userDTOMapper, passwordEncoder, s3Service, s3Buckets);
        this.productService = productService;
    }


    public List<Product> getProductList(Long sellerId) {
        return productService.getAllProductsBySeller(sellerId);
    }


    public void register(RegistrationRequest request) {
        AppUser newUser = addUser(request, AppUserRole.SELLER);
    }

    public void addProduct(ProductForm productForm, Long userId) {
        var product = Product.builder().productType(productForm.type())
                .name(productForm.name())
                .quantity(productForm.quantity())
                .price(productForm.price())
                .userId(userId).build();


    }


}




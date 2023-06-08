package com.codecool.backend.service;

import com.codecool.backend.model.products.Product;
import com.codecool.backend.model.products.ProductForm;
import com.codecool.backend.model.products.ProductDataAccessService;
import com.codecool.backend.model.products.ProductService;
import com.codecool.backend.model.users.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class SellerService extends AppUserService {
    private final ProductService productService;
@Autowired
    public SellerService(AppUserDao appUserDao, AppUserDTOMapper userDTOMapper, PasswordEncoder passwordEncoder, ProductService productService) {
        super(appUserDao, userDTOMapper, passwordEncoder);
        this.productService = productService;
    }


    public List<Product> getProductList(Long id){
      return  productService.getAllProductsBySeller(id);
    }


    public void register(RegistrationRequest request){
   AppUser newUser=addUser(request,AppUserRole.SELLER);
    }

    public void addProduct(ProductForm productForm,Long userId){
        var product=Product.builder().productType(productForm.type())
                .name(productForm.name())
                .quantity(productForm.quantity())
                .price(productForm.price())
                .userId(userId).build();



    }





}




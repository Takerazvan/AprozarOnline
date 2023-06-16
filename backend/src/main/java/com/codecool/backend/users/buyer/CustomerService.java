package com.codecool.backend.users.buyer;

import com.codecool.backend.products.shoppingcart.CartItem;
import com.codecool.backend.products.shoppingcart.ShoppingCart;
import com.codecool.backend.products.shoppingcart.ShoppingCartRepository;
import com.codecool.backend.s3.S3Buckets;
import com.codecool.backend.s3.S3Service;
import com.codecool.backend.users.*;
import com.codecool.backend.users.repository.AppUser;
import com.codecool.backend.users.repository.AppUserDTOMapper;
import com.codecool.backend.users.repository.AppUserDao;
import com.codecool.backend.users.repository.AppUserRole;
import com.codecool.backend.users.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CustomerService extends AppUserService {


    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public CustomerService(@Qualifier("jpa") AppUserDao appUserDao, AppUserDTOMapper userDTOMapper, PasswordEncoder passwordEncoder, S3Service s3Service, S3Buckets s3Buckets, ShoppingCartRepository shoppingCartRepository) {
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

    public void addProductToCart(CartItem item,Long userId){
        getCart(userId).addItem(item);
    }

}

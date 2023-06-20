package com.codecool.backend.users.buyer;

import com.codecool.backend.products.orders.CartItem;
import com.codecool.backend.products.shoppingcart.*;
import com.codecool.backend.s3.S3Buckets;
import com.codecool.backend.s3.S3Service;
import com.codecool.backend.users.repository.AppUserDTOMapper;
import com.codecool.backend.users.repository.AppUserDao;
import com.codecool.backend.users.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("customer")
public class CustomerService extends AppUserService {


    @Autowired
    public CustomerService(@Qualifier("jpa") AppUserDao appUserDao, AppUserDTOMapper userDTOMapper, PasswordEncoder passwordEncoder, S3Service s3Service, S3Buckets s3Buckets) {
        super(appUserDao, userDTOMapper, passwordEncoder, s3Service, s3Buckets);

    }

//
//    public ShoppingCartDTO getShoppingCartByUserId(Long id) {
//        return shoppingCartDAO.findCartByUserId(id);
//    }
//
//    public void addProductToCart(CartItem cartItem, Long userId){
//        shoppingCartDAO.addCartItem(cartItem,userId);
//    }
//
//    public void deleteProductFromCart(Long productId,Long userId){
//        shoppingCartDAO.removeCartItem(productId,userId);
//    }
//
//    public void updateCartItem(Integer quantity,Long id,Long productId){
//    shoppingCartDAO.updateItemQuantityByProductId(productId,quantity,id);
//    }
//    public void emptyShoppingCart(Long userId){
//        shoppingCartDAO.emptyCartItems(userId);
//    }

}

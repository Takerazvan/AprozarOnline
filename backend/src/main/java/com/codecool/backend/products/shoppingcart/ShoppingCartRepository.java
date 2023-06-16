package com.codecool.backend.products.shoppingcart;

import com.codecool.backend.products.shoppingcart.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    Optional<ShoppingCart> findByUserId(Long userId);

}

package com.codecool.backend.products.shoppingcart;

import com.codecool.backend.products.orders.CartItem;

public interface ShoppingCartDAO {

    ShoppingCartDTO findCartByUserId(Long userId);
    void addCartItem(CartItem item, Long userId);
    void removeCartItem(Long productId,Long userId);
    CartItem findCartItemBtProductId(Long productId,Long userId);

    void updateItemQuantityByProductId(Long productId,Integer quantity,Long userId);

    void emptyCartItems(Long userId);
}

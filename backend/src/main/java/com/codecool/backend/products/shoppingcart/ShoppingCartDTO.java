package com.codecool.backend.products.shoppingcart;

import com.codecool.backend.products.orders.CartItem;

import java.util.List;

public record ShoppingCartDTO(Double total, List<CartItem> items) {
}

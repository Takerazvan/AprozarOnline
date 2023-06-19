package com.codecool.backend.products.shoppingcart;

import java.util.List;

public record ShoppingCartDTO(Double total, List<CartItem> items) {
}

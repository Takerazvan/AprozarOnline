package com.codecool.backend.products.orders;

import java.util.List;

public record OrderRequest(List<CartItem> items,
                           String description,
                           String currency,
                           String address,
                           String paymentMethod,
                           String intent) {
}

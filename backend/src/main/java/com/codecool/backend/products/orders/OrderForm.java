package com.codecool.backend.products.orders;

import java.util.List;

public record OrderForm(List<CartItem> items,
                        Double total,
                        String description,
                        String currency,
                        String address,
                        String paymentMethod,
                        String intent) {
}

package com.codecool.backend.products.orders;

import java.util.List;

public record OrderDTO(Long orderId, List<CartItem> items, String address,Long userID, String paymentMethod) {
}

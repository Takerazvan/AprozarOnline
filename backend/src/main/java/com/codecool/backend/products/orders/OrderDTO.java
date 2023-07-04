package com.codecool.backend.products.orders;

import com.codecool.backend.products.orders.payments.paypal.PayPalContextDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OrderDTO implements Serializable {
    private final Long orderId;
    private final List<CartItem> items;
    private final String address;
    private final Long userID;
    private final String paymentMethod;

    private PayPalContextDTO applicationContext;
    private OrderIntent intent;
}

package com.codecool.backend.products.orders;

import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class OrderDTOMapper implements Function<com.codecool.backend.products.orders.Order, com.codecool.backend.products.orders.OrderDTO> {
    @Override
    public com.codecool.backend.products.orders.OrderDTO apply(Order order) {
        return new OrderDTO(
                order.getOrderId(),
                order.getCartItems(),
                order.getAddress(),
                order.getUserId(),
                order.getPaymentMethod().name()
        );
    }
}

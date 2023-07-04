package com.codecool.backend.products.orders;

import java.util.List;

public interface OrderDAO {
    List<com.codecool.backend.products.orders.OrderDTO> getAllOrdersByUser(Long userId);

    com.codecool.backend.products.orders.Order findByPaypalId(Long paypalId);

    com.codecool.backend.products.orders.OrderDTO addOrder(Order order);

    OrderDTO findOrderById(Long orderID);
}

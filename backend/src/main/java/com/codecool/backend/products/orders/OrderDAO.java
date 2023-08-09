package com.codecool.backend.products.orders;

import java.util.List;

public interface OrderDAO {
    List<com.codecool.backend.products.orders.OrderDTO> getAllOrdersByUser(Long userId);

    OrderRequest findByPaypalId(Long paypalId);

    com.codecool.backend.products.orders.OrderDTO addOrder(OrderRequest order);

    OrderDTO findOrderById(Long orderID);
}

package com.codecool.backend.products.orders;

import java.util.List;

public interface OrderDAO {
    List<OrderDTO> getAllOrdersByUser(Long userId);

    OrderDTO addOrder(Order order);
    OrderDTO findOrderById(Long orderID);
}

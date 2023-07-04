package com.codecool.backend.products.orders;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<com.codecool.backend.products.orders.Order,Long> {
Optional<com.codecool.backend.products.orders.Order> findByPaypalOrderId(Long id);
    Optional<List<Order>> findAllByUserId(Long aLong);
}

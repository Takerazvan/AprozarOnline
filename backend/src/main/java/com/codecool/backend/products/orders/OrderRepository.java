package com.codecool.backend.products.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface OrderRepository extends JpaRepository<OrderRequest,Long> {
Optional<OrderRequest> findByPaypalOrderId(Long id);
    Optional<List<OrderRequest>> findAllByUserId(Long aLong);
}

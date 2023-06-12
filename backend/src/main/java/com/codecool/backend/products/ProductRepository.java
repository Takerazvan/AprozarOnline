package com.codecool.backend.products;

import com.codecool.backend.products.Types.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByProductType(ProductType type);

    List<Product> findProductsByUserId(Long userId);


    List<Product> findProductsByNameContainingIgnoreCase(String name);


}

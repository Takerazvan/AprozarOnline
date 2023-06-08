package com.codecool.backend.model.products;

import com.codecool.backend.model.products.Product;
import com.codecool.backend.model.products.Types.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findProductsByProductType(ProductType type);

    public List<Product> findProductsByUserId(Long userId);


    public List<Product> findProductsByNameContainingIgnoreCase(String name);


}

package com.codecool.backend.repository;

import com.codecool.backend.model.Produce.Product;
import com.codecool.backend.model.Produce.Types.ProductType;
import com.codecool.backend.model.Users.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findProductsByProductType(ProductType type);

    public List<Product> findProductsBySeller(Seller seller);

    public List<Product> findProductsByNameContainingIgnoreCase(String name);
}

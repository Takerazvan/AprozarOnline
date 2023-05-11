package com.codecool.aprozar.repository;
import com.codecool.aprozar.model.Produce.Product;
import com.codecool.aprozar.model.Produce.Types.ProductType;
import com.codecool.aprozar.model.Users.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    public List<Product> findProductsByProductType(ProductType type);

    public List<Product> findProductsBySeller(Seller seller);

    public List<Product> findProductsByNameContainingIgnoreCase(String name);
}

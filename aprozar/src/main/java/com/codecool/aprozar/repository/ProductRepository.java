package com.codecool.aprozar.repository;
import com.codecool.aprozar.model.Produce.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}

package com.codecool.backend.products;

import com.codecool.backend.products.Types.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByProductType(ProductType type);

    List<Product> findProductsByUserId(Long userId);


    List<Product> findProductsByNameContainingIgnoreCase(String name);

    boolean existsProductById(Long productId);
//    @Modifying(clearAutomatically = true)
//    @Query("UPDATE Product c SET c.productImageId = ?1 WHERE c.id = ?2")
//    int updateProfileImageId(String profileImageId, Long customerId);


}

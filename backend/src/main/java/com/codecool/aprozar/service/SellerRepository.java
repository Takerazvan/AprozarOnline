package com.codecool.aprozar.service;
import com.codecool.aprozar.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SellerRepository extends JpaRepository<Seller,Long> {
}

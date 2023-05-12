package com.codecool.aprozar.service;
import com.codecool.aprozar.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}

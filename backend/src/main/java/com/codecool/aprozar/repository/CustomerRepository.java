package com.codecool.aprozar.repository;
import com.codecool.aprozar.model.Users.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}

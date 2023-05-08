package com.codecool.aprozar.service;


import com.codecool.aprozar.model.Customer;
import com.codecool.aprozar.model.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
public class CustomerService {
    private CustomerRepository customerRepository;
@Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers(){
    return customerRepository.findAll();
    }
    public void addCustomer(Customer customer){
        customerRepository.save(customer);
    }

}

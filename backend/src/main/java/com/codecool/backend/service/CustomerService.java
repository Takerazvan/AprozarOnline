package com.codecool.backend.service;
import com.codecool.backend.model.Users.Customer;
import com.codecool.backend.repository.CustomerRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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


    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void addCustomer(Customer customer) {
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        customerRepository.save(customer);
    }


    public void removeCustomer(Long customerId) {
        customerRepository.delete(getCustomerByID(customerId));
    }

    public Customer getCustomerByID(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Person does not exist"));
    }


    public Customer updateUser(Long id, Customer user) {
        Customer existingUser = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        existingUser.setAddress(user.getAddress());
        existingUser.setName(user.getName());
        existingUser.setBankAccount(user.getBankAccount());
        existingUser.setPhoneNumber(user.getPhoneNumber());

        return customerRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        customerRepository.deleteById(id);
    }

}

package com.codecool.aprozar.controller;

import com.codecool.aprozar.model.Users.Customer;
import com.codecool.aprozar.model.Produce.OrderItem;
import com.codecool.aprozar.model.Produce.Product;
import com.codecool.aprozar.service.CustomerService;
import com.codecool.aprozar.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {


    private final CustomerService customerService;

    private ProductService productService;
    @Autowired
    public CustomerController(CustomerService customerService, ProductService productService) {
        this.customerService = customerService;
        this.productService = productService;
    }
    @GetMapping("/products")
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }


    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomerByID(customerId);
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return ResponseEntity.ok(customer);
    }


    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        customerService.removeCustomer(customerId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{customerId}/shoppingCart/")
    public void addToShoppingCart(@PathVariable Long customerId,@RequestBody OrderItem orderItem){
        customerService.addToCart(orderItem,customerId);
    }


    public void removeFromShoppingCart(@PathVariable Long customerId,@RequestBody OrderItem orderItem){
        customerService.takeOutFromCart(orderItem,customerId);
    }

    @PutMapping("/{customerId}")
    public void updateUser(@PathVariable Long customerId,@RequestBody Customer customer){
        customerService.updateUser(customerId,customer);
    }
}

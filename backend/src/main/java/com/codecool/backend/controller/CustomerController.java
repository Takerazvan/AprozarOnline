//package com.codecool.backend.controller;
//
//import com.codecool.backend.model.Produce.Product;
//import com.codecool.backend.model.Users.Customer;
//import com.codecool.backend.service.CustomerService;
//import com.codecool.backend.service.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/customers")
//public class CustomerController {
//
//
//    private final CustomerService customerService;
//
//    private final ProductService productService;
//    @Autowired
//    public CustomerController(CustomerService customerService, ProductService productService) {
//        this.customerService = customerService;
//        this.productService = productService;
//    }
//    @GetMapping("/products")
//    public List<Product> getProducts(){
//        return productService.getAllProducts();
//    }
//
//
//    @GetMapping
//    public ResponseEntity<List<Customer>> getAllCustomers() {
//        List<Customer> customers = customerService.getAllCustomers();
//        return ResponseEntity.ok(customers);
//    }
//
//    @GetMapping("/{customerId}")
//    public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
//        Customer customer = customerService.getCustomerByID(customerId);
//        return ResponseEntity.ok(customer);
//    }
//
//    @PostMapping
//    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
//        try {
//            customerService.addCustomer(customer);
//            return ResponseEntity.ok(customer);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body("Email already exists");
//        }
//    }
//
//
//    @DeleteMapping("/{customerId}")
//    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
//        customerService.removeCustomer(customerId);
//        return ResponseEntity.noContent().build();
//    }
//
//
//
//
//    @PutMapping("/{customerId}")
//    public void updateUser(@PathVariable Long customerId,@RequestBody Customer customer){
//        customerService.updateUser(customerId,customer);
//    }
//}

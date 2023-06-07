package com.codecool.backend.controller;
import com.codecool.backend.model.users.AppUser;
import com.codecool.backend.security.RegistrationRequest;
import com.codecool.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class CustomerController {


    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;

    }


    @GetMapping("/{customerId}")
    public ResponseEntity<AppUser> getCustomerById(@PathVariable Long customerId) {
        AppUser customer = customerService.findAppUserById(customerId);
        return ResponseEntity.ok(customer);
    }
    @PostMapping("/api/auth/register")
    public ResponseEntity<?> addCustomer(@RequestBody RegistrationRequest request) {
        try {
            customerService.register(request);
            return ResponseEntity.ok("Succesful registration");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
    }
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteUser(customerId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{customerId}")
    public void updateUser(@PathVariable Long customerId,@RequestBody AppUser user){
        customerService.updateAppUser(customerId,user);
    }


}

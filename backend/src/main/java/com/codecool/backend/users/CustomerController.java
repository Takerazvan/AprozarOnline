package com.codecool.backend.users;
import com.codecool.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {


    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;

    }


    @GetMapping("/{customerId}")
    public ResponseEntity<AppUserDTO> getCustomerById(@PathVariable Long customerId) {
        AppUserDTO customer = customerService.getUser(customerId);
        return ResponseEntity.ok(customer);
    }
//    @PostMapping("/api/auth/register")
//    public ResponseEntity<?> addCustomer(@RequestBody RegistrationRequest request) {
//        try {
//            customerService.registerCustomer(request);
//            return ResponseEntity.ok("Succesful registration");
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body("Email already exists");
//        }
//    }
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{customerId}")
    public void updateUser(@PathVariable Long customerId,@RequestBody UpdateRequest updateRequest){
        customerService.updateCustomer(customerId,updateRequest);
    }


}

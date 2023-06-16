package com.codecool.backend.users.buyer;
import com.codecool.backend.users.UpdateRequest;
import com.codecool.backend.users.repository.AppUserDTO;
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

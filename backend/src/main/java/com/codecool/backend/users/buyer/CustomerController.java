package com.codecool.backend.users.buyer;

import com.codecool.backend.products.orders.OrderDTO;
import com.codecool.backend.products.orders.OrderForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {


    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;

    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<OrderDTO>> getShoppingCartByUserId(@PathVariable Long userId){
      List<OrderDTO> orders= customerService.getOrdersForThisCustomer(userId);
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/{userId}/makeOrder")
    public ResponseEntity<OrderDTO> addProductToCart(@PathVariable Long userId, @RequestBody OrderForm orderRequest){
        customerService.makeOrder(orderRequest,userId);
        return ResponseEntity.noContent().build();
    }

}

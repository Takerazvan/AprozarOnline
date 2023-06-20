package com.codecool.backend.users.buyer;

import com.codecool.backend.products.orders.CartItem;
import com.codecool.backend.products.shoppingcart.ShoppingCartDTO;
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

    @GetMapping("/{userId}/shoppingCart")
    public ResponseEntity<ShoppingCartDTO> getShoppingCartByUserId(@PathVariable Long userId){
       ShoppingCartDTO shoppingCartDTO= customerService.getShoppingCartByUserId(userId);
        return ResponseEntity.ok(shoppingCartDTO);
    }

    @PostMapping("/{userId}/shoppingCart")
    public ResponseEntity<Void> addProductToCart(@PathVariable Long userId, @RequestBody CartItem cartItem){
        customerService.addProductToCart(cartItem,userId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{userId}/delete")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long userId,@RequestBody Long productId)
    {
        customerService.deleteProductFromCart(productId,userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}/shoppingCart/{productId}")
    public ResponseEntity<Void> updateCartItem(@PathVariable Long userId,@PathVariable Long productId,@RequestBody Integer quantity){
        customerService.updateCartItem(quantity,userId,productId);
        return ResponseEntity.noContent().build();
    }

}

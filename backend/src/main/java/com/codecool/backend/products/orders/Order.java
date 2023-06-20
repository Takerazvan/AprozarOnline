package com.codecool.backend.products.orders;

import com.codecool.backend.products.shoppingcart.CartItem;
import com.codecool.backend.products.shoppingcart.ShoppingCart;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long orderId;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
    private List<CartItem> cartItems;
    private Double total;
    private String address;


}

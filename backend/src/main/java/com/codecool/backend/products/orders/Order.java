package com.codecool.backend.products.orders;

import com.codecool.backend.products.shoppingcart.CartItem;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long orderId;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "shoppingCart", orphanRemoval = true)
    private List<CartItem> cartItems;
    private Double total;
    private String address;


}

package com.codecool.backend.products.shoppingcart;

import com.codecool.backend.products.Product;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "cartItem", orphanRemoval = true)
    private Product product;
    private Integer quantity = 1;
    @ManyToOne(fetch = FetchType.EAGER)
    private ShoppingCart shoppingCart;

}
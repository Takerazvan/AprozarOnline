package com.codecool.backend.model.products;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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


    public void increaseQuantity() {
        quantity++;
    }

    public boolean decreaseQuantity() {
        var check = true;
        if (quantity > 1) {
            quantity--;
        } else {
            check = false;
        }
        return check;
    }

}
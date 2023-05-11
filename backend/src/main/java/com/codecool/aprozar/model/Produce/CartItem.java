package com.codecool.aprozar.model.Produce;

import jakarta.persistence.*;
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
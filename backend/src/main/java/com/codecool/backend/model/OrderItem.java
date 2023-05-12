package com.codecool.backend.model;

import com.codecool.backend.model.Produce.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {

    private Product product;
    private Integer quantity = 1;


    public void increaseQuantity() {
        quantity++;
    }

    public void decreaseQuantity() {

        if (quantity > 1) {
            quantity--;
        }
    }

}
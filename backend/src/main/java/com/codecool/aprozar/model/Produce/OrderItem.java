package com.codecool.aprozar.model.Produce;

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
package com.codecool.aprozar.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity

public class Customer extends Person {

//    private ShoppingCart shoppingCart;

    public Customer(String name, String adress, String bankAccount, String phoneNumber) {
        super(name, adress, bankAccount, phoneNumber);
//        this.shoppingCart = new ShoppingCart();
    }


}

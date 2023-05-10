package com.codecool.aprozar.model.Users;

import com.codecool.aprozar.model.Produce.ShoppingCart;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Customer extends Person {




    public Customer(String name, String adress, String bankAccount, String phoneNumber, ShoppingCart shoppingCart) {
        super(name, adress, bankAccount, phoneNumber);
    }


}

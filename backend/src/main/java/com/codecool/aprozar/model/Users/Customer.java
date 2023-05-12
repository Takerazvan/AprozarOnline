package com.codecool.aprozar.model.Users;

import com.codecool.aprozar.model.Produce.ShoppingCart;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Customer extends Person {

@OneToOne(fetch = FetchType.EAGER)
private ShoppingCart shoppingCart;



    public Customer(String name, String adress, String bankAccount, String phoneNumber, ShoppingCart shoppingCart) {
        super(name, adress, bankAccount, phoneNumber);
        this.shoppingCart=new ShoppingCart(this);
    }


}

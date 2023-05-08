package com.codecool.aprozar.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Getter
@Setter
@NoArgsConstructor
@Entity

public class Customer extends Person {


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    private List<Product> productBasket;


    public Customer(String name, String adress, String bankAccount, String phoneNumber) {
        super(name, adress, bankAccount, phoneNumber);
        this.productBasket = new ArrayList<>();
    }

    public void addProductBasket(List<Product> productBasket) {
        this.productBasket = productBasket;
    }



}

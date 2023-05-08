package com.codecool.aprozar.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Entity

public class Customer extends Person {


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    private List<Products> productBasket;


    public Customer(String name, String adress, String bankAccount, String phoneNumber) {
        super(name, adress, bankAccount, phoneNumber);
        this.productBasket = new ArrayList<>();
    }


    public void addProductBasket(List<Products> productBasket) {
        this.productBasket = productBasket;
    }



}

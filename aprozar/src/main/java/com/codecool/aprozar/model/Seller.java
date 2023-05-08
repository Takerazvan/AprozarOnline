package com.codecool.aprozar.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@NoArgsConstructor
@Entity
public class Seller extends Person {

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "seller")
    private List<Products> availableProducts;
    Seller( String name, String adress, String bankAccount, String phoneNumber) {
        super( name, adress, bankAccount, phoneNumber);
        this.availableProducts = new ArrayList<>();
    }

    public List<Products> getAvailableProducts() {
        return availableProducts;
    }

    public void setAvailableProducts(List<Products> availableProducts) {
        if (isInstock()) {
            this.availableProducts = availableProducts;
        } else {
            throw new IllegalArgumentException("Product Not In Stock");
        }
    }
    public boolean isInstock(){
        return !availableProducts.isEmpty();
    }
}

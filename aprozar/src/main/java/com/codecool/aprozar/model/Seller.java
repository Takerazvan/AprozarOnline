package com.codecool.aprozar.model;


import com.codecool.aprozar.model.Product;
import jakarta.persistence.*;
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
    private List<Product> availableProducts;
    Seller( String name, String adress, String bankAccount, String phoneNumber) {
        super( );
        this.availableProducts = new ArrayList<>();
    }

    public List<Product> getAvailableProducts() {
        return availableProducts;
    }

    public void setAvailableProducts(List<Product> availableProducts) {
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

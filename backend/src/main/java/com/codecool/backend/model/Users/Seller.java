package com.codecool.backend.model.Users;



import com.codecool.backend.model.Produce.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Seller extends Person {

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "seller", orphanRemoval = true)
    @JsonIgnoreProperties("seller")
    private List<Product> availableProducts;

    Seller( String name, String adress, String bankAccount, String phoneNumber) {
        super( );
        this.availableProducts = new ArrayList<>();
    }
}

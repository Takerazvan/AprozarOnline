package com.codecool.aprozar.model;
import com.codecool.aprozar.model.Customer;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Seller seller;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
    private  String name;

    public Product(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}

package com.codecool.aprozar.model;
import com.codecool.aprozar.model.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import aprozar.src.main.java.com.codecool.aprozar.model.Types.ProductType;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
private ProductType productType;
    @ManyToOne(fetch = FetchType.EAGER)
    private Seller seller;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
    private  String name;



}

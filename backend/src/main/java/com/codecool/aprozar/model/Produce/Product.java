package com.codecool.aprozar.model.Produce;
import com.codecool.aprozar.model.Users.Customer;
import com.codecool.aprozar.model.Users.Seller;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.codecool.aprozar.model.Produce.Types.ProductType;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double price;
    private int quantity;
    private ProductType productType;
    @ManyToOne(fetch = FetchType.EAGER)
    private Seller seller;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
    private  String name;



}

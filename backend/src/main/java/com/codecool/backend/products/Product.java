package com.codecool.backend.products;

import com.codecool.backend.products.Types.ProductType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double price;
    private int quantity;
    private ProductType productType;

    @OneToOne(fetch = FetchType.EAGER)
    private CartItem cartItem;
    private  String name;
    private Long userId;
    private String productFilepath;
    private String productDescription;

}

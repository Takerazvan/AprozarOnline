package com.codecool.backend.products;

import com.codecool.backend.fileStorage.Image;
import com.codecool.backend.products.Types.ProductType;
import com.codecool.backend.products.orders.CartItem;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    private String productDescription;
    @OneToMany(mappedBy = "product",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Image> productImages;

}

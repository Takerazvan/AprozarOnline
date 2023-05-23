package com.codecool.backend.model.products;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.codecool.backend.model.products.Types.ProductType;
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

    @OneToOne(fetch = FetchType.EAGER)
    private CartItem cartItem;
    private  String name;
    private Long userId;

}

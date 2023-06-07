package com.codecool.backend.model.products;
import jakarta.persistence.*;
import lombok.*;
import com.codecool.backend.model.products.Types.ProductType;

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

}

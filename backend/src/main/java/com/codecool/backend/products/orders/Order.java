package com.codecool.backend.products.orders;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
    private List<CartItem> cartItems;
    private Double total;
    private String address;
    private Long userId;
    private PaymentMethod paymentMethod;
    private String currency;
    private String description;
    private String intent;

    public Order(List<CartItem> cartItems, Double total, String address, Long userId, PaymentMethod paymentMethod) {
        this.cartItems = cartItems;
        this.total = getTotal();
        this.address = address;
        this.userId = userId;
        this.paymentMethod = paymentMethod;
    }

    public double getTotal() {
        return cartItems.stream().mapToDouble(cartItem -> cartItem.getProduct().getPrice()).sum();
    }
}

package com.codecool.backend.products.orders;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@Entity
public class OrderRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    private List<CartItem> cartItems;

    private Double total;
    private String address;
    private Long userId;
    private PaymentMethod paymentMethod;
    private String currency;
    private String description;
    private String intent;
    private Long paypalOrderId;
    private String paypalOrderStatus;

    public OrderRequest(Long orderId, List<CartItem> cartItems,
                 Double total, String address,
                 Long userId, PaymentMethod paymentMethod,
                 String currency, String description,
                 String intent, Long paypalOrderId, String paypalOrderStatus) {
        this.orderId = orderId;
        this.cartItems = cartItems;
        this.total = total;
        this.address = address;
        this.userId = userId;
        this.paymentMethod = paymentMethod;
        this.currency = currency;
        this.description = description;
        this.intent = intent;
        this.paypalOrderId = paypalOrderId;
        this.paypalOrderStatus = paypalOrderStatus;
    }
}

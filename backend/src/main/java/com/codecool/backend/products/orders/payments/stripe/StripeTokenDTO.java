package com.codecool.backend.products.orders.payments.stripe;

import lombok.Data;

@Data
public class StripeTokenDTO {
    private String cardNumber;
    private String expMonth;
    private String expYear;
    private String cvc;
    private String token;
    private String username;
    boolean success;
}

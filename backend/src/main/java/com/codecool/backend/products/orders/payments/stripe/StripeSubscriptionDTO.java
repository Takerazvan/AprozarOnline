package com.codecool.backend.products.orders.payments.stripe;

public record StripeSubscriptionDTO(
         String cardNumber,
         String expMonth,
         String expYear,
         String cvc,
         String email,
         String priceId,
         String username,
         long numberOfLicense,
         boolean success

) {
}

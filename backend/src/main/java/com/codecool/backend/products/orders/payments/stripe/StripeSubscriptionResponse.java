package com.codecool.backend.products.orders.payments.stripe;

public record StripeSubscriptionResponse(
      String stripeCustomerId,
         String stripeSubscriptionId,
         String stripePaymentMethodId,
        String username

) {
}

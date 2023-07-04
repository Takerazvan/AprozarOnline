package com.codecool.backend.products.orders.payments.stripe;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class StripeChargeDTO{

        private String  stripeToken;
        private String  username;
        private Double  amount;
        private Boolean success;
        private String  message;
        private String chargeId;
        private Map<String,Object> additionalInfo = new HashMap<>();


}

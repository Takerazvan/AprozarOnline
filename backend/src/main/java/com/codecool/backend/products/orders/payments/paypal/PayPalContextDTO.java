package com.codecool.backend.products.orders.payments.paypal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PayPalContextDTO {
    @JsonProperty("brand_name")
    private String brandName;
    @JsonProperty("landing_page")
    private String landingPage;
    @JsonProperty("return_url")
    private String returnUrl;
    @JsonProperty("cancel_url")
    private String cancelUrl;
}

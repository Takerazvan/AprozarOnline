package com.codecool.backend.products.orders.payments.paypal;

public enum PayPalEndpoints {
    GET_ACCESS_TOKEN("/auth/token"),
    GET_CLIENT_TOKEN("/auth/generate-token"),
    ORDER_CHECKOUT("/checkout/orders");
    private final String path;

    PayPalEndpoints(String path) {
        this.path = path;
    }

    public static String createURL(String baseUrl, PayPalEndpoints endpoint)
    {
        return baseUrl+endpoint.path;
    }
    public static String createUrl(String baseUrl, PayPalEndpoints endpoint, String... params) {
        return baseUrl + String.format(endpoint.path, params);
    }
}

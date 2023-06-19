package com.codecool.backend.products;

import com.codecool.backend.products.Types.ProductType;

public record ProductForm(
        String name,
        Integer quantity,

        Double price,

        ProductType type,

        String productDescription

) {

}

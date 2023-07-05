package com.codecool.backend.products;

import com.codecool.backend.products.Types.ProductType;

import java.util.List;

public record ProductDTO(
        Long id,
        Double price,
        int quantity,
        ProductType productType,

        String name,

        Long sellerId



) {


}

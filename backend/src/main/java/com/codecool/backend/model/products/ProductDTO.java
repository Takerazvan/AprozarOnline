package com.codecool.backend.model.products;

import com.codecool.backend.model.products.Types.ProductType;

public record ProductDTO(
        Long id,
        Double price,
        int quantity,
        ProductType productType,

        String name,

        Long sellerId) {


}

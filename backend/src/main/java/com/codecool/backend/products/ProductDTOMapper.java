package com.codecool.backend.products;

import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class ProductDTOMapper implements Function<Product,ProductDTO> {
    @Override
    public ProductDTO apply(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getPrice(),
                product.getQuantity(),
                product.getProductType(),
                product.getName(),
                product.getUserId()
        );
    }
}

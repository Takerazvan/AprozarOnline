package com.codecool.backend.model.products;

import com.codecool.backend.model.products.Types.ProductType;

public record ProductForm (
     String name,
     Integer quantity,

     Double price,

     ProductType type

){

}

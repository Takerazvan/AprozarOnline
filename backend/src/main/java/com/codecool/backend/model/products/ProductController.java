package com.codecool.backend.model.products;

import com.codecool.backend.model.products.Types.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController

@RequestMapping("/api/products")
class ProductController {
    private final ProductDataAccessService productService;

    @Autowired
    public ProductController(ProductDataAccessService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.findProductById(id);
    }

    @GetMapping("{productType}")
    public List<Product> getProductsByCategory(@PathVariable("type") ProductType productType) {
        return productService.getProductsByCategory(productType);
    }


}


package com.codecool.backend.products;

import com.codecool.backend.products.Types.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController

@RequestMapping("/api/products")
class ProductController {
    private final ProductDataAccessService productService;

    @Autowired
    public ProductController(ProductDataAccessService productService) {
        this.productService = productService;
    }

//    @GetMapping
//    public ResponseEntity<List<Product> >getProducts() {
//        List<Product> products=productService.getAllProducts();
//        System.out.println(products);
//        return ResponseEntity.ok(products);
//    }

//    @GetMapping("/{id}")
//    public Optional<Product> getProductById(@PathVariable("id") Long id) {
//        return productService.findProductById(id);
//    }
//
//    @GetMapping("{productType}")
//    public List<Product> getProductsByCategory(@PathVariable("type") ProductType productType) {
//        return productService.getProductsByCategory(productType);
//    }
//
//    @GetMapping("/{sellerId}")
//    public List<Product> getAllProductsBySeller(@PathVariable("id") Long id) {
//        return productService.getAllProductsBySeller(id);
//    }
}


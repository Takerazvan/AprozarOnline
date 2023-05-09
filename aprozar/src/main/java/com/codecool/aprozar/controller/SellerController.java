package com.codecool.aprozar.controller;

import com.codecool.aprozar.model.Product;
import com.codecool.aprozar.model.Seller;
import com.codecool.aprozar.service.ProductService;
import com.codecool.aprozar.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SellerService sellerService;


    @GetMapping("/{sellerId}/products")
    public ResponseEntity<List<Product>> getSellerProducts(@PathVariable Long sellerId) {
        Seller seller = sellerService.getSellerByID(sellerId);
        List<Product> products = seller.getAvailableProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/{sellerId}/products")
    public ResponseEntity<Product> addProduct(@PathVariable Long sellerId, @RequestBody Product product) {
        Seller seller = sellerService.getSellerByID(sellerId);
        product.setSeller(seller);
        seller.getAvailableProducts().add(product);
        productService.addProduct(product);
        return ResponseEntity.ok(product);
    }
}
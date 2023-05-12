package com.codecool.backend.controller;

import com.codecool.backend.model.Produce.Product;
import com.codecool.backend.model.Users.Seller;
import com.codecool.backend.service.ProductService;
import com.codecool.backend.service.SellerService;
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
        seller.getAvailableProducts().add(product);
        product.setSeller(seller);
        productService.addProduct(product);
        return ResponseEntity.ok(product);
    }
}
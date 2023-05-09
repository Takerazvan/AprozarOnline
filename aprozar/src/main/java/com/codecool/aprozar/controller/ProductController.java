package com.codecool.aprozar.controller;

import com.codecool.aprozar.model.Product;
import com.codecool.aprozar.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
@Autowired
    public ProductController(ProductService productService) {
    this.productService=productService;
    }

    @GetMapping
    public List<Product> getProducts(){
    return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id){
    return productService.findProductById(id);
    }


}

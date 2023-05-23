package com.codecool.backend.service;

import com.codecool.backend.model.products.Product;
import com.codecool.backend.repository.ProductRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Getter
@Setter
@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void addProducts(List<Product> products) {
        productRepository.saveAll(products);
    }

    public List<Product> getAllProductsBySeller(Long id){
        return productRepository.findProductsByUserId(id);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public Product findProductById(Long Id) {
        return productRepository.findById(Id).orElseThrow(() -> new IllegalArgumentException("Unavailable Product"));
    }


}

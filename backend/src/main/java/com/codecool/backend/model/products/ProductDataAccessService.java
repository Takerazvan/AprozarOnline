package com.codecool.backend.model.products;

import com.codecool.backend.model.products.Types.ProductType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Getter
@Setter
@Service
public class ProductDataAccessService implements ProductDAO {

    private ProductRepository productRepository;

    @Autowired
    public ProductDataAccessService(ProductRepository productRepository) {
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

public List<Product> getProductsByCategory(ProductType productType){
        return productRepository.findProductsByProductType(productType);
}

public void deleteProductById(Long productId){
        productRepository.deleteById(productId);
}
}

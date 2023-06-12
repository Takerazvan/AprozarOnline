package com.codecool.backend.products;

import com.codecool.backend.products.Types.ProductType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Product> getAllProductsBySeller(Long id) {
        return productRepository.findProductsByUserId(id);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public Optional<Product> findProductById(Long Id) {
        return productRepository.findById(Id);
    }

    public List<Product> getProductsByCategory(ProductType productType) {
        return productRepository.findProductsByProductType(productType);
    }

    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }
}

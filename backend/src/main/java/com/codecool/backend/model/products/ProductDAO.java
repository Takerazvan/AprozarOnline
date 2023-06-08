package com.codecool.backend.model.products;

import com.codecool.backend.model.products.Types.ProductType;

import java.util.List;

public interface ProductDAO {

    public List<Product> getAllProducts();

    public void addProducts(List<Product> products);

    public List<Product> getAllProductsBySeller(Long id);

    public void addProduct(Product product);

    public Product findProductById(Long Id);

    public List<Product> getProductsByCategory(ProductType productType);

    public void deleteProduct(Product product);

}

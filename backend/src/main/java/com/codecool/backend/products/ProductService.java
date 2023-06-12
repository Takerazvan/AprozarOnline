package com.codecool.backend.products;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class ProductService {

    private ProductDAO productDAO;
    private ProductDTOMapper productDTOMapper;


    public void addProducts(List<Product> products){
        productDAO.addProducts(products);
    }

    public List<ProductDTO> getAllProducts(){
        return productDAO.getAllProducts().stream().map(productDTOMapper).collect(Collectors.toList());
    }

    public void addProduct(Product product) {
        productDAO.addProduct(product);
    }

    public void deleteProduct(Long productId) {
        productDAO.findProductById(productId);
    }

    public List<Product> getAllProductsBySeller(Long sellerId) {
        return productDAO.getAllProductsBySeller(sellerId);
    }

//    public byte[] uploadCustomerProductImage(Long productId){
//        var  product=productDAO.findProductById(productId)
//                .map(productDTOMapper)
//                .orElseThrow(()->new ResourceNotFoundException(
//                        "product with id [%s] not found".formatted(productId)
//                ));
//        return default;
//
//        TODO: implement on aws image storage
//    }

}

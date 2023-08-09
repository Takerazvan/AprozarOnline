package com.codecool.backend.products;

import com.codecool.backend.exception.ResourceNotFoundException;
import com.codecool.backend.fileStorage.ImageService;
import com.codecool.backend.products.Types.ProductType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ImageService imageService;
    private ProductDAO productDAO;
    private ProductDTOMapper productDTOMapper;


    public List<ProductDTO> getAllProducts() {
        return productDAO.getAllProducts().stream().map(productDTOMapper).collect(Collectors.toList());
    }

    public void addProduct(Product product, MultipartFile multipartFile) {
       try{
           String url = imageService.upload(multipartFile);
           product.setProductUrl(url);
           productDAO.addProduct(product);
       } catch (IOException e) {
           throw new RuntimeException(e);
       }




    }

    public void deleteProduct(Long productId) {
        checkIfProductExists(productId);
        productDAO.deleteProductById(productId);

    }

    public List<ProductDTO> getAllProductsBySeller(Long sellerId) {
        return productDAO.getAllProductsBySeller(sellerId).stream().map(productDTOMapper).collect(Collectors.toList());
    }

    private void checkIfProductExists(Long productId) {
        if (!productDAO.existProductById(productId)) {
            throw new ResourceNotFoundException("product with id [%s] not found".formatted(productId));
        }
    }

    public void uploadProductImage(Long productId, MultipartFile file) {

        try {
            Product product = productDAO.findProductById(productId).orElseThrow(() -> new ResourceNotFoundException(
                    "product with id [%s] not found".formatted(productId)
            ));
            String photoUrl = imageService.upload(file);
            product.setProductUrl(photoUrl);
            productDAO.addProduct(product);
        } catch (IOException e) {
            throw new RuntimeException("failed to upload profile image", e);
        }


    }

//    public List<byte[]> getProductImage(Long productId) {
//
//return imageService.listByUser(productId);
//    }

    public ProductDTO getProductById(Long productId) {
        return productDAO.findProductById(productId).map(productDTOMapper).orElseThrow(() -> new ResourceNotFoundException(
                "product with id [%s] not found".formatted(productId)
        ));
    }

    public void updateProduct(Long productId, ProductForm productForm) {
        Product product = productDAO.findProductById(productId).orElseThrow(() -> new ResourceNotFoundException(
                "product with id [%s] not found".formatted(productId)));
        boolean isModified = false;
        if (!Objects.equals(productForm.name(), product.getName())) {
            product.setName(productForm.name());
            isModified = true;
        }

        if (!Objects.equals(productForm.price(), product.getPrice())) {
            product.setPrice(productForm.price());
            isModified = true;
        }

        if (productForm.type() != null && productForm.type() != product.getProductType()) {
            product.setProductType(productForm.type());
            isModified = true;
        }

        if (!Objects.equals(productForm.productDescription(), product.getProductDescription())) {
            product.setProductDescription(productForm.productDescription());
            isModified = true;
        }

        if (isModified) {
            productDAO.updateProduct(product);
        }
    }

    public List<ProductDTO> getAllProductsByCategory(ProductType type) {
        return productDAO.getProductsByCategory(type).stream().map(productDTOMapper).collect(Collectors.toList());
    }
}


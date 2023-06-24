package com.codecool.backend.products;

import com.codecool.backend.fileStorage.S3Buckets;
import com.codecool.backend.fileStorage.S3Service;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final S3Service s3Service;
    private final S3Buckets s3Buckets;
    private ProductDAO productDAO;
    private ProductDTOMapper productDTOMapper;

    public void addProducts(List<Product> products) {
        productDAO.addProducts(products);
    }

    public List<ProductDTO> getAllProducts() {
        return productDAO.getAllProducts().stream().map(productDTOMapper).collect(Collectors.toList());
    }

    public void addProduct(Product product) {
        productDAO.addProduct(product);
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
        var product = productDAO.findProductById(productId)
                .map(productDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "product with id [%s] not found".formatted(productId)
                ));
        String productImageId = UUID.randomUUID().toString();
        try {
            s3Service.putObject(s3Buckets.getProduct(), "profile-images/%s/%s".formatted(productId, productImageId),
                    file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("failed to upload profile image", e);
        }
        productDAO.updateProductImageId(productImageId, productId);

    }

    public byte[] getProductImage(Long productId) {
        ProductDTO product = productDAO.findProductById(productId)
                .map(productDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "product with id [%s] not found".formatted(productId)
                ));


        if (StringUtils.isBlank(product.profileImageId())) {
            throw new ResourceNotFoundException(
                    "customer with id [%s] profile image not found".formatted(productId));
        }

        byte[] productImage = s3Service.getObject(
                s3Buckets.getProduct(),
                "product-images/%s/%s".formatted(productId, product.profileImageId())

        );
        return productImage;
    }

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
}


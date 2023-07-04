package com.codecool.backend.products;

import com.codecool.backend.fileStorage.Image;
import com.codecool.backend.fileStorage.ImageService;
import com.codecool.backend.fileStorage.aws.S3Buckets;
import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.utils.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ImageService s3Service;
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
                .orElseThrow(() -> new ResourceNotFoundException(
                        "product with id [%s] not found".formatted(productId)
                ));

        try {
        Image image=s3Service.upload(file);
        List<Image> updatedimages=product.getProductImages();
        updatedimages.add(image);
     product.setProductImages(updatedimages);
     productDAO.addProduct(product);
        } catch (IOException e) {
            throw new RuntimeException("failed to upload profile image", e);
        }


    }

    public List<byte[]> getProductImage(Long productId) {
        Product product = productDAO.findProductById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "product with id [%s] not found".formatted(productId)
                ));


        if (product.getProductImages() != null) {
            throw new ResourceNotFoundException(
                    "customer with id [%s] image not found".formatted(productId));
        }

        return product.getProductImages().stream()
                .map(image -> {
                    try {
                        return s3Service.download(image.getId());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());

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


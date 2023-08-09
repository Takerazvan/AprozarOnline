package com.codecool.backend.users.seller;

import com.codecool.backend.fileStorage.ImageService;
import com.codecool.backend.products.Product;
import com.codecool.backend.products.ProductDTO;
import com.codecool.backend.products.ProductForm;
import com.codecool.backend.products.ProductService;
import com.codecool.backend.products.Types.ProductType;
import com.codecool.backend.security.jwt.JWTService;
import com.codecool.backend.users.repository.AppUserDTO;
import com.codecool.backend.users.repository.AppUserDTOMapper;
import com.codecool.backend.users.repository.AppUserDao;
import com.codecool.backend.users.repository.AppUserRole;
import com.codecool.backend.users.service.AppUserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service("seller")
public class SellerService extends AppUserService {
    private final ProductService productService;


    public SellerService(@Qualifier("jpa") AppUserDao appUserDao,JWTService jwtService, AppUserDTOMapper userDTOMapper, PasswordEncoder passwordEncoder, ImageService imageService,  ProductService productService) {
        super(appUserDao, userDTOMapper, passwordEncoder, imageService, jwtService);
        this.productService = productService;
    }

    public List<ProductDTO> getProductList(Long sellerId) {
        return productService.getAllProductsBySeller(sellerId);
    }

    public void addProduct(ProductForm productForm, Long userId) {
        var product = Product.builder().productType(productForm.type())
                .name(productForm.name())
                .quantity(productForm.quantity())
                .price(productForm.price())
                .userId(userId).build();
        System.out.println(productForm);
        productService.addProduct(product,productForm.photos());

    }

    public void deleteProduct(Long productID) {

        productService.deleteProduct(productID);
    }

    public void uploadProductImage(Long productId, MultipartFile file) {
        productService.uploadProductImage(productId, file);
    }
public ProductDTO getProductById(Long productId){
        return productService.getProductById(productId);
}
    public void updateProduct(Long productId, ProductForm productForm){
        productService.updateProduct(productId,productForm);
    }

    public List<ProductDTO> getProductsByCategory(ProductType productType){
       return productService.getAllProductsByCategory(productType);
    }

    public List<AppUserDTO> getSellers(){
        return getUsersByRole(AppUserRole.SELLER);
    }



}





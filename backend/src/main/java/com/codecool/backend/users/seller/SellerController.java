package com.codecool.backend.users.seller;

import com.codecool.backend.products.ProductDTO;
import com.codecool.backend.products.ProductForm;
import com.codecool.backend.products.ProductService;
import com.codecool.backend.users.repository.AppUserDao;
import com.codecool.backend.users.service.AppUserService;
import com.codecool.backend.users.service.UserController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("seller/")
public class SellerController extends UserController {

    private final SellerService service;
    private final ProductService productService;

    public SellerController(@Qualifier("seller") AppUserService appUserService, SellerService service, ProductService productService) {
        super(appUserService);
        this.service = service;
        this.productService = productService;

    }

    @GetMapping("/myproducts")
    public ResponseEntity<List<ProductDTO>> getMyProducts(Long sellerId){
        List<ProductDTO> myProducts=service.getProductList(sellerId);
        return ResponseEntity.ok(myProducts);
    }
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductDTO>> getProduct(@PathVariable Long productId) {
        List<ProductDTO> product =productService.getAllProductsBySeller(productId);
        return ResponseEntity.ok(product);
    }


    @PostMapping("/addProduct")
    public ResponseEntity<Void> addProduct(ProductForm productForm,Long userId){
        service.addProduct(productForm,userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(Long productId){
        service.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{productId}/update")
    public ResponseEntity<Void> updateProduct(@PathVariable Long productId,@RequestBody ProductForm productForm){
       service.updateProduct(productId,productForm);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{productId}/productImage")
public ResponseEntity<Void> uploadImage(@PathVariable Long productId, @RequestParam("image") MultipartFile file){
        service.uploadProductImage(productId,file);
        return ResponseEntity.noContent().build();
    }




}

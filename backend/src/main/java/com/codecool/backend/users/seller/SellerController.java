package com.codecool.backend.users.seller;

import com.codecool.backend.products.ProductDTO;
import com.codecool.backend.products.ProductForm;
import com.codecool.backend.users.repository.AppUserDTO;
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

    public SellerController(@Qualifier("seller") AppUserService appUserService, SellerService service) {
        super(appUserService);
        this.service = service;
    }

    @GetMapping("/myproducts")
    public ResponseEntity<List<ProductDTO>> getMyProducts(Long sellerId){
        List<ProductDTO> myProducts=service.getProductList(sellerId);
        return ResponseEntity.ok(myProducts);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AppUserDTO>> getAllSellers(){
        List <AppUserDTO>  seller= service.findUsersByRole();
        return  ResponseEntity.ok(seller);
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
public ResponseEntity<Void> uploadImage(@PathVariable Long productId, @RequestBody MultipartFile file){
        service.uploadProductImage(productId,file);
        return ResponseEntity.noContent().build();
    }




}

package com.codecool.backend.service;

import com.codecool.backend.model.Users.Seller;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
public class SellerService {
    private SellerRepository sellerRepository;
@Autowired
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public List<Seller> getAllSellers(){
        return sellerRepository.findAll();
    }
    public void addSeller(Seller seller){
        sellerRepository.save(seller);
    }

    public Seller getSellerByID(Long id){
        return sellerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Person does not exist"));
    }
}
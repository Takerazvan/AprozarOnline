package com.codecool.backend.service;

import com.codecool.backend.model.users.AppUserRepository;
import com.codecool.backend.model.users.AppUserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class SellerService {

    private final ProductService productService;


}




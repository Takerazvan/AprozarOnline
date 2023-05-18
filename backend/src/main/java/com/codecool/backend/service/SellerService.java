package com.codecool.backend.service;

import com.codecool.backend.model.users.AppUserRepository;
import org.springframework.stereotype.Service;


@Service
public class SellerService extends BaseUserService {


    public SellerService(AppUserRepository appUserRepository) {
        super(appUserRepository);
    }
}


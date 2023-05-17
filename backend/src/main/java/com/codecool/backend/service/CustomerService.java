package com.codecool.backend.service;
import com.codecool.backend.model.users.AppUserRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends BaseUserService {


    public CustomerService(AppUserRepository appUserRepository) {
        super(appUserRepository);
    }
}

package com.codecool.backend.users.seller;

import com.codecool.backend.users.service.AppUserService;
import com.codecool.backend.users.service.UserController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("seller/")
public class SellerController extends UserController {

    private final SellerService service;

    public SellerController(@Qualifier("seller") AppUserService appUserService, SellerService service) {
        super(appUserService);
        this.service = service;
    }

}

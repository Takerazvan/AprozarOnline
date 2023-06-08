package com.codecool.backend.model.users;

import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class AppUserDTOMapper implements Function<AppUser,AppUserDTO> {


    @Override
    public AppUserDTO apply(AppUser appUser) {
        return new AppUserDTO(appUser.getId(),
                appUser.getFirstName(),
                appUser.getLastName(),
                appUser.getEmail(),
                appUser.getPassword(),
                appUser.getAppUserRole()
                );
    }
}

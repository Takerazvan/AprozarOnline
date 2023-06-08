package com.codecool.backend.model.users;

import java.util.List;
import java.util.Optional;

public interface AppUserDao {

    List<AppUser> getAllCustomers();
    Optional<AppUser> getCustomerById(Long appUserId);

    void addAppUser(AppUser appUser);

    boolean isAppUserWithEmail(String email);

    boolean isAppUserWithId(Long id);

    void deleteAppUserById(Long Id);

    void updateAppUser(AppUser update);

    Optional<AppUser> findUserByEmail(String email);

}

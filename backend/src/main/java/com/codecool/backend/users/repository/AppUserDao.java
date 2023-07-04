package com.codecool.backend.users.repository;

import com.codecool.backend.users.repository.AppUser;

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

    List<AppUser> findUsersByRole(AppUserRole appUserRole);




}

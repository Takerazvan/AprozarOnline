package com.codecool.backend.users.service;

import com.codecool.backend.users.repository.AppUser;
import com.codecool.backend.users.repository.AppUserDao;
import com.codecool.backend.users.repository.AppUserRepository;
import com.codecool.backend.users.repository.AppUserRole;
import com.codecool.backend.users.repository.AppUserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jpa")
@AllArgsConstructor
public class AppUserDataAccessService implements AppUserDao {
    private final AppUserRepository appUserRepository;
    @Override
    public List<AppUser> getAllCustomers() {
        return appUserRepository.findAll();
    }

    @Override
    public Optional<AppUser> getCustomerById(Long appUserId) {
        return appUserRepository.findById(appUserId);
    }

    @Override
    public void addAppUser(AppUser appUser) {
appUserRepository.save(appUser);
    }

    @Override
    public boolean isAppUserWithEmail(String email) {
        return appUserRepository.existsAppUserByEmail(email);
    }



    @Override
    public boolean isAppUserWithId(Long id) {
        return appUserRepository.existsAppUserById(id);
    }

    @Override
    public void deleteAppUserById(Long Id) {
appUserRepository.deleteById(Id);
    }

    @Override
    public void updateAppUser(AppUser appUser) {
appUserRepository.save(appUser);
    }

    @Override
    public Optional<AppUser> findUserByEmail(String email) {
        return appUserRepository.findAppUserByEmail(email);
    }

    @Override
    public List<AppUser> findUsersByRole(AppUserRole appUserRole) {
        return appUserRepository.findAppUsersByAppUserRole(appUserRole);
    }


}

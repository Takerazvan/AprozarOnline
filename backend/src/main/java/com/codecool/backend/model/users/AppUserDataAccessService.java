package com.codecool.backend.model.users;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
@AllArgsConstructor
public class AppUserDataAccessService implements AppUserDao{
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
}

package com.codecool.backend.repository;


import com.codecool.backend.model.users.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    public Optional<AppUser> findAppUserByEmail(String email);

}

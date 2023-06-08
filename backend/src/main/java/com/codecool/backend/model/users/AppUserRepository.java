package com.codecool.backend.model.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    boolean existsAppUserByEmail(String email);
    boolean existsAppUserById(Long id);
    Optional<AppUser> findAppUserByEmail(String email);
}

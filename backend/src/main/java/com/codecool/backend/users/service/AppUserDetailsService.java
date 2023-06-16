package com.codecool.backend.users.service;

import com.codecool.backend.users.repository.AppUserDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final AppUserDao userDao;

    public AppUserDetailsService(@Qualifier("jpa") AppUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Username " + username + " not found"));
    }
}

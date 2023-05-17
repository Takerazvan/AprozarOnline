package com.codecool.backend.service;

import com.codecool.backend.model.users.AppUser;
import com.codecool.backend.model.users.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class BaseUserService {
    private AppUserRepository appUserRepository;


    public List<AppUser> getAllUsers(){
        return appUserRepository.findAll();
    }

    public void addUser(AppUser user){
        appUserRepository.save(user);
    }

    public AppUser findUserById(Long id){
      return   appUserRepository.findById(id).orElseThrow(()->new NoSuchElementException("User doesn't exist"));
    }

 public void updateUser(AppUser newUser,Long id){

 }
}

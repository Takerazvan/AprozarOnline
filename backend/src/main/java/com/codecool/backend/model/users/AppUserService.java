package com.codecool.backend.model.users;

import com.sun.jdi.request.DuplicateRequestException;
import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AppUserService {

    private final AppUserDao appUserDao;
    private final AppUserDTOMapper userDTOMapper;
    private final PasswordEncoder passwordEncoder;

    public List<AppUserDTO> getAllCustomers(){
        return appUserDao.getAllCustomers()
                .stream().map(userDTOMapper)
                .collect(Collectors.toList());
    }

    public AppUserDTO getUser(Long id){
        return appUserDao.getCustomerById(id)
                .map(userDTOMapper)
                .orElseThrow(()->new ResourceNotFoundException());
    }

    public AppUser addUser(RegistrationRequest registrationRequest, AppUserRole role){
        String email=registrationRequest.email();
        if(appUserDao.isAppUserWithEmail(email)){
            throw new DuplicateRequestException(
                    "email already taken"
            );
        }

        AppUser appUser=AppUser.builder()
                .firstName(registrationRequest.firstName())
                .lastName(registrationRequest.lastName())
                .email(registrationRequest.email())
                .password(passwordEncoder.encode(registrationRequest.password()))
                .appUserRole(role)
                .build();

        appUserDao.addAppUser(appUser);

        return appUser;
    }

    public void deleteCustomerById(Long userId){
        checkIUserExistsOrNot(userId);
        appUserDao.deleteAppUserById(userId);
    }


    private void checkIUserExistsOrNot(Long id){
        if(!appUserDao.isAppUserWithId(id)){
            throw  new ResourceNotFoundException(
                    "customer with id [%s] not found".formatted(id));
        }
    }

    public void updateCustomer(Long userId,UpdateRequest updateRequest){
        AppUser appUser = appUserDao.getCustomerById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Customer with id [%s] not found", userId)
                ));

        boolean isModified = false;

        if (updateRequest.firstname() != null) {
            appUser.setFirstName(updateRequest.firstname());
            isModified = true;
        }

        if (updateRequest.lastname() != null) {
            appUser.setLastName(updateRequest.lastname());
            isModified = true;
        }

        if (updateRequest.email() != null) {
            if (appUserDao.isAppUserWithEmail(updateRequest.email())) {
                throw new DuplicateRequestException("Email already taken");
            }
            appUser.setEmail(updateRequest.email());
            isModified = true;
        }

        if (isModified) {
            appUserDao.updateAppUser(appUser);
        }
    }

}

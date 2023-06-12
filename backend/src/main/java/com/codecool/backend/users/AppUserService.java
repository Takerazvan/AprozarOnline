package com.codecool.backend.users;

import com.codecool.backend.s3.S3Buckets;
import com.codecool.backend.s3.S3Service;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppUserService {

    private final AppUserDao appUserDao;
    private final AppUserDTOMapper userDTOMapper;
    private final PasswordEncoder passwordEncoder;
    private final S3Service s3Service;
    private final S3Buckets s3Buckets;

    public AppUserService(@Qualifier("jdbc") AppUserDao appUserDao, AppUserDTOMapper userDTOMapper, PasswordEncoder passwordEncoder, S3Service s3Service, S3Buckets s3Buckets) {
        this.appUserDao = appUserDao;
        this.userDTOMapper = userDTOMapper;
        this.passwordEncoder = passwordEncoder;
        this.s3Service = s3Service;
        this.s3Buckets = s3Buckets;
    }

    public List<AppUserDTO> getAllCustomers() {
        return appUserDao.getAllCustomers()
                .stream().map(userDTOMapper)
                .collect(Collectors.toList());
    }

    public AppUserDTO getUser(Long id) {
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

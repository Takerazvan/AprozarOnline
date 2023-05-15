package com.codecool.backend.model.Users;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
abstract class Person {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
    private  String name;

    private  String address;
    private String email;
    private  String bankAccount;

    private  String phoneNumber;


    Person( String name, String adress,String email, String bankAccount, String phoneNumber) {

        this.name = name;
        this.address = adress;
        this.email=email;
        this.bankAccount = bankAccount;
        this.phoneNumber = phoneNumber;
    }
    Person() {
        this("","", "", "", "");
    }


}

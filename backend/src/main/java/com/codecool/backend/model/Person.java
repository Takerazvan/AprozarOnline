package com.codecool.backend.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Person {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
    private  String name;

    private  String address;
    private  String bankAccount;

    private  String phoneNumber;


    Person( String name, String adress, String bankAccount, String phoneNumber) {

        this.name = name;
        this.address = adress;
        this.bankAccount = bankAccount;
        this.phoneNumber = phoneNumber;
    }
    Person() {
        this("", "", "", "");
    }


}

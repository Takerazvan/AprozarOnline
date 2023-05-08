package com.codecool.aprozar.model;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;


import java.util.UUID;
@Getter
@Setter
@MappedSuperclass
abstract class Person {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
    private  String name;

    private  String adress;
    private  String bankAccount;

    private  String phoneNumber;


    Person( String name, String adress, String bankAccount, String phoneNumber) {

        this.name = name;
        this.adress = adress;
        this.bankAccount = bankAccount;
        this.phoneNumber = phoneNumber;
    }
    Person() {
        this("", "", "", "");
    }


}

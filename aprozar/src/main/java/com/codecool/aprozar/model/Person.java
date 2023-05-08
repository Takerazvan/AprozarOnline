package com.codecool.aprozar.model;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;


import java.util.UUID;

@MappedSuperclass
abstract class Person {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
    private final String name;

    private final String adress;
    private final String bankAccount;

    private final String phoneNumber;


    Person( String name, String adress, String bankAccount, String phoneNumber) {

        this.name = name;
        this.adress = adress;
        this.bankAccount = bankAccount;
        this.phoneNumber = phoneNumber;
    }
    Person() {
        this("", "", "", "");
    }



    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public long getId() {
        return id;
    }
}

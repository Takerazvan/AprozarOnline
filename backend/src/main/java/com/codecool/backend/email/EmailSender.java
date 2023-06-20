package com.codecool.backend.email;

public interface EmailSender {
    void send(String to, String message);
}
package com.codecool.backend.notifications;

public interface MessageSender {
    void send(String to, String message);
}
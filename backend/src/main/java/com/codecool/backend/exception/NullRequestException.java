package com.codecool.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NullRequestException extends RuntimeException{
    public NullRequestException(String message) {
        super(message);
    }
}

package com.example.refazendoapi.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class LocalException extends RuntimeException{
    private final HttpStatus httpStatus;

    public LocalException( HttpStatus httpStatus, final String message) {
        this.httpStatus = httpStatus;
    }
}

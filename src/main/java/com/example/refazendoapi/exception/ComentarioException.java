package com.example.refazendoapi.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class ComentarioException extends RuntimeException{
    private HttpStatus httpStatus;

    public ComentarioException(HttpStatus httpStatus, String mensagem){
        this.httpStatus = httpStatus;
    }
}

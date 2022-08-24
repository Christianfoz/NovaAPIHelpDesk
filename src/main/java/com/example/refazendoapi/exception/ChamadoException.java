package com.example.refazendoapi.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ChamadoException extends RuntimeException{
    private HttpStatus httpStatus;

    public ChamadoException(HttpStatus httpStatus, String mensagem){
        this.httpStatus = httpStatus;

    }
}

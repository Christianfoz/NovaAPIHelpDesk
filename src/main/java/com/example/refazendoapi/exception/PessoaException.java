package com.example.refazendoapi.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class PessoaException extends RuntimeException{
    private HttpStatus httpStatus;

    public PessoaException(HttpStatus httpStatus, String mensagem){
        this.httpStatus = httpStatus;
    }

}

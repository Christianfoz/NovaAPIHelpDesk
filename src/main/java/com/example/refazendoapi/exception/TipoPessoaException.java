package com.example.refazendoapi.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class TipoPessoaException extends RuntimeException{
    private HttpStatus httpStatus;

    public TipoPessoaException(HttpStatus httpStatus, String mensagem){
        this.httpStatus = httpStatus;
    }
}

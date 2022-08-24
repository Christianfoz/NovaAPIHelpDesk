package com.example.refazendoapi.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class SituacaoException extends RuntimeException{
    private HttpStatus httpStatus;

    public SituacaoException(HttpStatus httpStatus, String mensagem){
        this.httpStatus = httpStatus;
    }
}

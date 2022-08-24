package com.example.refazendoapi.handler;

import com.example.refazendoapi.exception.*;
import com.example.refazendoapi.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ResourceHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response>
    handlerMethodArgumentNotValidException(MethodArgumentNotValidException m) {
        Map<String, String> erros = new HashMap<>();
        m.getBindingResult().getAllErrors().forEach((erro) -> {
            String campo = ((FieldError) erro).getField();
            String mensagem = erro.getDefaultMessage();
            erros.put(campo, mensagem);
        });
        Response<Map<String, String>> response = new Response<>();
        response.setData(erros);
        response.setTimeStamp(LocalDateTime.now());
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }



    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Response<String>> handlerAccessDeniedException(AccessDeniedException a){
        Response<String> response = new Response();
        response.setStatusCode(HttpStatus.FORBIDDEN.value());
        response.setData(a.getMessage());
        response.setTimeStamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(response);
    }

    @ExceptionHandler(ChamadoException.class)
    public ResponseEntity<Response<String>> handlerChamadoException(ChamadoException c){
        Response<String> response = new Response();
        response.setStatusCode(c.getHttpStatus().value());
        response.setData(c.getMessage());
        response.setTimeStamp(LocalDateTime.now());
        return ResponseEntity.status(c.getHttpStatus()).body(response);
    }

    @ExceptionHandler(ComentarioException.class)
    public ResponseEntity<Response<String>> handlerComentarioException(ComentarioException c){
        Response<String> response = new Response<>();
        response.setTimeStamp(LocalDateTime.now());
        response.setStatusCode(c.getHttpStatus().value());
        response.setData(c.getMessage());
        return ResponseEntity.status(c.getHttpStatus()).body(response);
    }

    @ExceptionHandler(LocalException.class)
    public ResponseEntity<Response<String>> handlerLocalException(LocalException l) {
        Response<String> response = new Response<>();
        response.setStatusCode(l.getHttpStatus().value());
        response.setData(l.getMessage());
        response.setTimeStamp(LocalDateTime.now());
        return ResponseEntity.status(l.getHttpStatus()).body(response);
    }

    @ExceptionHandler(PessoaException.class)
    public ResponseEntity<Response<String>> handlerPessoaException(PessoaException p){
        Response<String> response = new Response<>();
        response.setStatusCode(p.getHttpStatus().value());
        response.setData(p.getMessage());
        response.setTimeStamp(LocalDateTime.now());
        return ResponseEntity.status(p.getHttpStatus()).body(response);
    }

    @ExceptionHandler(SituacaoException.class)
    public ResponseEntity<Response<String>> handlerSituacaoException(SituacaoException s){
        Response<String> response = new Response<>();
        response.setStatusCode(s.getHttpStatus().value());
        response.setTimeStamp(LocalDateTime.now());
        response.setData(s.getMessage());
        return ResponseEntity.status(s.getHttpStatus()).body(response);
    }

    @ExceptionHandler(TipoPessoaException.class)
    public ResponseEntity<Response<String>> handlerTipoPessoaException(TipoPessoaException t){
        Response<String> response = new Response<>();
        response.setTimeStamp(LocalDateTime.now());
        response.setStatusCode(t.getHttpStatus().value());
        response.setData(t.getMessage());
        return ResponseEntity.status(t.getHttpStatus()).body(response);
    }



}

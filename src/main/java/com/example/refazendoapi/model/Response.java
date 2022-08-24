package com.example.refazendoapi.model;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Data
public class Response<T> extends RepresentationModel {
    private int statusCode;
    private T data;
    private LocalDateTime timeStamp;
}

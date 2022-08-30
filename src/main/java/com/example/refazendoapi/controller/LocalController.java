package com.example.refazendoapi.controller;

import com.example.refazendoapi.config.SwaggerConfig;
import com.example.refazendoapi.model.Local;
import com.example.refazendoapi.model.Response;
import com.example.refazendoapi.service.LocalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(path = "/local")
@Api(tags = {SwaggerConfig.LOCAL})
public class LocalController {
    @Autowired
    private LocalService localService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar Local")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Response<Local>> buscarLocalPorId(@PathVariable("id") UUID id){
        Response<Local> response = new Response<>();
        response.setData(localService.buscarPorId(id));
        response.setStatusCode(HttpStatus.OK.value());
        response.setTimeStamp(LocalDateTime.now());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(LocalController.class)
                .criarLocal(null)).withRel("CREATE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(LocalController.class)
                .buscarLocais()).withRel("ALL"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(LocalController.class)
                .buscarLocalPorId(id)).withSelfRel());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(LocalController.class)
                .deletarLocal(id)).withRel("DELETE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(LocalController.class)
                .atualizarLocal(response.getData().getIdLocal(), response.getData())).withRel("UPDATE"));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Buscar Locais")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public ResponseEntity<Response<Iterable<Local>>> buscarLocais(){
        Response<Iterable<Local>> response = new Response<>();
        response.setData(localService.listarLocais());
        response.setStatusCode(HttpStatus.OK.value());
        response.setTimeStamp(LocalDateTime.now());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(LocalController.class)
                .criarLocal(null)).withRel("POST"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(LocalController.class)
                .buscarLocais()).withSelfRel());
        for (Local local: response.getData()) {
            local.add(linkTo(WebMvcLinkBuilder.methodOn(LocalController.class)
                    .buscarLocalPorId(local.getIdLocal())).withRel("GET"));
            local.add(linkTo(WebMvcLinkBuilder.methodOn(LocalController.class)
                    .atualizarLocal(local.getIdLocal(), local)).withRel("UPDATE"));
            local.add(linkTo(WebMvcLinkBuilder.methodOn(LocalController.class)
                    .deletarLocal(local.getIdLocal())).withRel("DELETE"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    @ApiOperation(value = "Criar Local")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Response<Local>> criarLocal(@Valid @RequestBody Local local){
        Response<Local> response = new Response<>();
        response.setData(localService.criarLocal(local));
        response.setTimeStamp(LocalDateTime.now());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(LocalController.class)
                .criarLocal(null)).withSelfRel());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(LocalController.class)
                .buscarLocais()).withRel("ALL"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(LocalController.class)
                .buscarLocalPorId(response.getData().getIdLocal())).withRel("GET"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(LocalController.class)
                .deletarLocal(response.getData().getIdLocal())).withRel("DELETE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(LocalController.class)
                .atualizarLocal(response.getData().getIdLocal(), response.getData())).withRel("UPDATE"));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar local")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response<Local>> atualizarLocal(@PathVariable("id") UUID id,@Valid @RequestBody Local local){
        Response<Local> response = new Response<>();
        response.setData(localService.editarLocal(id, local));
        response.setStatusCode(HttpStatus.OK.value());
        response.setTimeStamp(LocalDateTime.now());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(LocalController.class)
                .criarLocal(null)).withRel("CREATE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(LocalController.class)
                .buscarLocais()).withRel("ALL"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(LocalController.class)
                .buscarLocalPorId(response.getData().getIdLocal())).withRel("GET"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(LocalController.class)
                .deletarLocal(response.getData().getIdLocal())).withRel("DELETE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(LocalController.class)
                .atualizarLocal(response.getData().getIdLocal(), response.getData())).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar Local")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response<Local>> deletarLocal(@PathVariable("id") UUID id){
        Response<Local> response = new Response<>();
        response.setData(localService.deletarLocal(id));
        response.setTimeStamp(LocalDateTime.now());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(LocalController.class)
                .criarLocal(null)).withRel("CREATE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(LocalController.class)
                .buscarLocais()).withRel("ALL"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(LocalController.class)
                .buscarLocalPorId(response.getData().getIdLocal())).withRel("GET"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(LocalController.class)
                .deletarLocal(response.getData().getIdLocal())).withSelfRel());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(LocalController.class)
                .atualizarLocal(response.getData().getIdLocal(), response.getData())).withRel("UPDATE"));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

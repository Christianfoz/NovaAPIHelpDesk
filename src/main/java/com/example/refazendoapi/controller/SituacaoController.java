package com.example.refazendoapi.controller;

import com.example.refazendoapi.config.SwaggerConfig;
import com.example.refazendoapi.model.Response;
import com.example.refazendoapi.model.Situacao;
import com.example.refazendoapi.service.SituacaoService;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(path = "situacao")
@Api(tags = {SwaggerConfig.SITUACAO})
public class SituacaoController {
    @Autowired
    private SituacaoService situacaoService;

    @ApiOperation(value = "Listar Situações")
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response<Iterable<Situacao>>> listarSituacoes(){
        Response<Iterable<Situacao>> response = new Response<>();
        response.setData(situacaoService.listarSituacoes());
        response.setTimeStamp(LocalDateTime.now());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class)
                .criarSituacao(null)).withRel("CREATE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class)
                .listarSituacoes()).withSelfRel());
        for (Situacao situacao : response.getData()){
            response.add(linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class)
                    .buscarSituacao(situacao.getIdSituacao())).withRel("GET"));
            response.add(linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class)
                    .deletarSituacao(situacao.getIdSituacao())).withRel("DELETE"));
            response.add(linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class)
                    .atualizarSituacao(situacao.getIdSituacao(), situacao)).withRel("UPDATE"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Buscar Situação")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response<Situacao>> buscarSituacao(@PathVariable("id") int id){
        Response<Situacao> response = new Response<>();
        response.setData(situacaoService.buscarSituacao(id));
        response.setTimeStamp(LocalDateTime.now());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class)
                .criarSituacao(null)).withRel("CREATE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class)
                .listarSituacoes()).withRel("ALL"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class)
                .buscarSituacao(id)).withSelfRel());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class)
                .deletarSituacao(id)).withRel("DELETE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class)
                .atualizarSituacao(response.getData().getIdSituacao(), response.getData())).withRel("UPDATE"));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Criar Situação")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Response<Situacao>> criarSituacao(@Valid @RequestBody Situacao situacao){
        Response<Situacao> response = new Response<>();
        response.setData(situacaoService.criarSituacao(situacao));
        response.setStatusCode(HttpStatus.OK.value());
        response.setTimeStamp(LocalDateTime.now());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class)
                .criarSituacao(null)).withSelfRel());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class)
                .listarSituacoes()).withRel("ALL"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class)
                .buscarSituacao(situacao.getIdSituacao())).withRel("GET"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class)
                .deletarSituacao(situacao.getIdSituacao())).withRel("DELETE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class)
                .atualizarSituacao(response.getData().getIdSituacao(), response.getData())).withRel("UPDATE"));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Atualizar Situação")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response<Situacao>> atualizarSituacao(@PathVariable("id") int id, @Valid @RequestBody Situacao situacao){
        Response<Situacao> response = new Response<>();
        response.setData(situacaoService.atualizarSituacao(id, situacao));
        response.setStatusCode(HttpStatus.OK.value());
        response.setTimeStamp(LocalDateTime.now());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class)
                .criarSituacao(null)).withRel("CREATE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class)
                .listarSituacoes()).withRel("ALL"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class)
                .buscarSituacao(id)).withRel("GET"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class)
                .deletarSituacao(id)).withRel("DELETE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class)
                .atualizarSituacao(response.getData().getIdSituacao(), response.getData())).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Deletar Situação")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response<Situacao>> deletarSituacao(@PathVariable("id") int id){
        Response<Situacao> response = new Response<>();
        response.setData(situacaoService.deletarSituacao(id));
        response.setTimeStamp(LocalDateTime.now());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class)
                .criarSituacao(null)).withRel("CREATE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class)
                .listarSituacoes()).withRel("ALL"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class)
                .buscarSituacao(id)).withRel("GET"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class)
                .deletarSituacao(id)).withSelfRel());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(SituacaoController.class)
                .atualizarSituacao(response.getData().getIdSituacao(), response.getData())).withRel("UPDATE"));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

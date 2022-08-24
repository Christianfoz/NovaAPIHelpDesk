package com.example.refazendoapi.controller;

import com.example.refazendoapi.config.SwaggerConfig;
import com.example.refazendoapi.model.Response;
import com.example.refazendoapi.model.TipoPessoa;
import com.example.refazendoapi.service.TipoPessoaService;
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
@RequestMapping(path = "/tipo")
@Api(tags = {SwaggerConfig.TIPO_PESSOA})
public class TipoPessoaController {
    @Autowired
    private TipoPessoaService tipoPessoaService;

    @GetMapping
    @ApiOperation("Listar Tipos")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response<Iterable<TipoPessoa>>> listarTipos(){
        Response<Iterable<TipoPessoa>> response = new Response<>();
        response.setData(tipoPessoaService.listarTipos());
        response.setTimeStamp(LocalDateTime.now());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(TipoPessoaController.class)
                .criarTipo(null)).withRel("CREATE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(TipoPessoaController.class)
                .listarTipos()).withRel("ALL"));
        for(TipoPessoa tipoPessoa: response.getData()){
            response.add(linkTo(WebMvcLinkBuilder.methodOn(TipoPessoaController.class)
                    .buscarTipo(tipoPessoa.getIdTipo())).withRel("GET"));
            response.add(linkTo(WebMvcLinkBuilder.methodOn(TipoPessoaController.class)
                    .deletarTipo(tipoPessoa.getIdTipo())).withRel("DELETE"));
            response.add(linkTo(WebMvcLinkBuilder.methodOn(TipoPessoaController.class)
                    .atualizarTipo(tipoPessoa.getIdTipo(), tipoPessoa)).withRel("UPDATE"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    @ApiOperation("Buscar Tipo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response<TipoPessoa>> buscarTipo(@PathVariable("id") int id){
        Response<TipoPessoa> response = new Response<>();
        response.setData(tipoPessoaService.buscarTipo(id));
        response.setTimeStamp(LocalDateTime.now());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(TipoPessoaController.class)
                .criarTipo(null)).withRel("CREATE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(TipoPessoaController.class)
                .listarTipos()).withRel("ALL"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(TipoPessoaController.class)
                .buscarTipo(id)).withSelfRel());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(TipoPessoaController.class)
                .deletarTipo(id)).withRel("DELETE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(TipoPessoaController.class)
                .atualizarTipo(id, response.getData())).withRel("UPDATE"));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    @ApiOperation("Criar Tipo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response<TipoPessoa>> criarTipo(@Valid @RequestBody TipoPessoa tipoPessoa){
        Response<TipoPessoa> response = new Response<>();
        response.setData(tipoPessoaService.criarTipo(tipoPessoa));
        response.setTimeStamp(LocalDateTime.now());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(TipoPessoaController.class)
                .criarTipo(null)).withSelfRel());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(TipoPessoaController.class)
                .listarTipos()).withRel("ALL"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(TipoPessoaController.class)
                .buscarTipo(tipoPessoa.getIdTipo())).withRel("GET"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(TipoPessoaController.class)
                .deletarTipo(tipoPessoa.getIdTipo())).withRel("DELETE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(TipoPessoaController.class)
                .atualizarTipo(tipoPessoa.getIdTipo(), response.getData())).withRel("UPDATE"));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualizar Tipo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response<TipoPessoa>> atualizarTipo(@PathVariable("id") int id, @Valid @RequestBody TipoPessoa tipoPessoa){
        Response<TipoPessoa> response = new Response<>();
        response.setData(tipoPessoaService.atualizarTipo(id, tipoPessoa));
        response.setTimeStamp(LocalDateTime.now());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(TipoPessoaController.class)
                .criarTipo(null)).withRel("CREATE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(TipoPessoaController.class)
                .listarTipos()).withRel("ALL"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(TipoPessoaController.class)
                .buscarTipo(id)).withRel("GET"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(TipoPessoaController.class)
                .deletarTipo(id)).withRel("DELETE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(TipoPessoaController.class)
                .atualizarTipo(id, response.getData())).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deletar Tipo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response<TipoPessoa>> deletarTipo(@PathVariable("id") int id){
        Response<TipoPessoa> response = new Response<>();
        response.setData(tipoPessoaService.deletarTipo(id));
        response.setTimeStamp(LocalDateTime.now());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(TipoPessoaController.class)
                .criarTipo(null)).withRel("CREATE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(TipoPessoaController.class)
                .listarTipos()).withRel("ALL"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(TipoPessoaController.class)
                .buscarTipo(id)).withRel("GET"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(TipoPessoaController.class)
                .deletarTipo(id)).withSelfRel());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(TipoPessoaController.class)
                .atualizarTipo(id, response.getData())).withRel("UPDATE"));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

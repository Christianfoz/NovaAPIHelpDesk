package com.example.refazendoapi.controller;

import com.example.refazendoapi.config.SwaggerConfig;
import com.example.refazendoapi.model.Pessoa;
import com.example.refazendoapi.model.Response;
import com.example.refazendoapi.service.PessoaService;
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
@RequestMapping(path = "/pessoa")
@Api(tags = {SwaggerConfig.PESSOA})
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar Pessoa")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response<Pessoa>> buscarPessoa(@PathVariable("id") int id){
        Response<Pessoa> response = new Response<>();
        response.setData(pessoaService.buscarPorId(id));
        response.setTimeStamp(LocalDateTime.now());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
                .criarPessoa(null)).withRel("CREATE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
                .listarPessoa()).withRel("ALL"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
                .buscarPessoa(id)).withSelfRel());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
                .deletarPessoa(id)).withRel("DELETE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
                .atualizarPessoa(id, (Pessoa) response.getData())).withRel("UPDATE"));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    @ApiOperation(value = "Listar Pessoas")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response<Iterable<Pessoa>>> listarPessoa(){
        Response<Iterable<Pessoa>> response = new Response<>();
        response.setData(pessoaService.listarPessoas());
        response.setTimeStamp(LocalDateTime.now());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
                .criarPessoa(null)).withRel("CREATE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
                .listarPessoa()).withSelfRel());
        for (Pessoa pessoa: response.getData()){
            response.add(linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
                    .deletarPessoa(pessoa.getIdPessoa())).withRel("DELETE"));
            response.add(linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
                    .atualizarPessoa(pessoa.getIdPessoa(), (Pessoa) response.getData())).withRel("UPDATE"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    @ApiOperation(value = "Criar Pessoa")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Response<Pessoa>> criarPessoa(@Valid @RequestBody Pessoa pessoa){
        Response<Pessoa> response = new Response<>();
        response.setData(pessoaService.criarPessoa(pessoa));
        response.setTimeStamp(LocalDateTime.now());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
                .criarPessoa(null)).withSelfRel());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
                .listarPessoa()).withRel("ALL"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
                .buscarPessoa(pessoa.getIdPessoa())).withRel("GET"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
                .deletarPessoa(pessoa.getIdPessoa())).withRel("DELETE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
                .atualizarPessoa(pessoa.getIdPessoa(), response.getData())).withRel("UPDATE"));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar Pessoa")
    public ResponseEntity<Response<Pessoa>> atualizarPessoa(@PathVariable("id") int id, @Valid @RequestBody Pessoa pessoa){
        Response<Pessoa> response = new Response<>();
        response.setData(pessoaService.criarPessoa(pessoa));
        response.setTimeStamp(LocalDateTime.now());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
                .criarPessoa(null)).withRel("CREATE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
                .listarPessoa()).withRel("ALL"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
                .buscarPessoa(pessoa.getIdPessoa())).withRel("GET"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
                .deletarPessoa(pessoa.getIdPessoa())).withRel("DELETE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
                .atualizarPessoa(pessoa.getIdPessoa(), response.getData())).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar Pessoa")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response<Pessoa>> deletarPessoa(@PathVariable("id") int id){
        Response<Pessoa> response = new Response<>();
        response.setData(pessoaService.deletarPessoa(id));
        response.setTimeStamp(LocalDateTime.now());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
                .criarPessoa(null)).withRel("CREATE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
                .listarPessoa()).withRel("ALL"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
                .buscarPessoa(id)).withRel("GET"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
                .deletarPessoa(id)).withSelfRel());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
                .atualizarPessoa(id, response.getData())).withRel("UPDATE"));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}

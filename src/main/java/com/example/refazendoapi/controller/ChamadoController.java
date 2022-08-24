package com.example.refazendoapi.controller;

import com.example.refazendoapi.config.SwaggerConfig;
import com.example.refazendoapi.model.Chamado;
import com.example.refazendoapi.model.Response;
import com.example.refazendoapi.service.ChamadoService;
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
@RequestMapping(path = "/chamado")
@Api(tags = {SwaggerConfig.CHAMADO})
public class ChamadoController {
    @Autowired
    private ChamadoService chamadoService;

    @GetMapping
    @ApiOperation(value = "Listar Chamados")
    @PreAuthorize("hasAntRole('USER','ADMIN')")
    public ResponseEntity<Response<Iterable<Chamado>>> listarChamados(){
        Response<Iterable<Chamado>> response = new Response<>();
        response.setData(chamadoService.listarChamados());
        response.setTimeStamp(LocalDateTime.now());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ChamadoController.class)
                .criarChamado(null)).withRel("POST"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ChamadoController.class)
                .listarChamados()).withSelfRel());
        for (Chamado chamado: response.getData()) {
            chamado.add(linkTo(WebMvcLinkBuilder.methodOn(ChamadoController.class)
                    .buscarChamado(chamado.getIdChamado())).withRel("GET"));
            chamado.add(linkTo(WebMvcLinkBuilder.methodOn(ChamadoController.class)
                    .atualizarChamado(chamado.getIdChamado(), chamado)).withRel("UPDATE"));
            chamado.add(linkTo(WebMvcLinkBuilder.methodOn(ChamadoController.class)
                    .deletarChamado(chamado.getIdChamado())).withRel("DELETE"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ApiOperation(value = "Buscar Chamado")
    public ResponseEntity<Response<Chamado>> buscarChamado(@PathVariable("id") int id){
        Response<Chamado> response = new Response<>();
        response.setData(chamadoService.buscarChamado(id));
        response.setTimeStamp(LocalDateTime.now());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ChamadoController.class)
                .criarChamado(null)).withRel("CREATE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ChamadoController.class)
                .listarChamados()).withRel("ALL"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ChamadoController.class)
                .buscarChamado(id)).withSelfRel());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ChamadoController.class)
                .deletarChamado(id)).withRel("DELETE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ChamadoController.class)
                .atualizarChamado(response.getData().getIdChamado(), response.getData())).withRel("UPDATE"));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    @ApiOperation(value = "Criar Chamado")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Response<Chamado>> criarChamado(@Valid @RequestBody Chamado chamado){
        Response<Chamado> response = new Response<>();
        response.setData(chamadoService.criarChamado(chamado));
        response.setTimeStamp(LocalDateTime.now());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ChamadoController.class)
                .criarChamado(null)).withSelfRel());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ChamadoController.class)
                .listarChamados()).withRel("ALL"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ChamadoController.class)
                .buscarChamado(chamado.getIdChamado())).withRel("GET"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ChamadoController.class)
                .deletarChamado(chamado.getIdChamado())).withRel("DELETE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ChamadoController.class)
                .atualizarChamado(response.getData().getIdChamado(), response.getData())).withRel("UPDATE"));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar Chamado")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Response<Chamado>> atualizarChamado(@PathVariable("id") int id,@Valid @RequestBody Chamado chamado){
        Response<Chamado> response = new Response<>();
        response.setData(chamadoService.atualizarChamado(id, chamado));
        response.setTimeStamp(LocalDateTime.now());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ChamadoController.class)
                .criarChamado(null)).withRel("CREATE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ChamadoController.class)
                .listarChamados()).withRel("ALL"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ChamadoController.class)
                .buscarChamado(chamado.getIdChamado())).withRel("GET"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ChamadoController.class)
                .deletarChamado(chamado.getIdChamado())).withRel("DELETE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ChamadoController.class)
                .atualizarChamado(response.getData().getIdChamado(), response.getData())).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar Chamado")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Response<Chamado>> deletarChamado(@PathVariable("id") int id){
        Response<Chamado> response = new Response<>();
        response.setData(chamadoService.deletarChamado(id));
        response.setTimeStamp(LocalDateTime.now());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ChamadoController.class)
                .criarChamado(null)).withRel("CREATE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ChamadoController.class)
                .listarChamados()).withRel("ALL"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ChamadoController.class)
                .buscarChamado(id)).withRel("GET"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ChamadoController.class)
                .deletarChamado(id)).withSelfRel());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ChamadoController.class)
                .atualizarChamado(response.getData().getIdChamado(), response.getData())).withRel("UPDATE"));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

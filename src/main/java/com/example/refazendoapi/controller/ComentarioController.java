package com.example.refazendoapi.controller;

import com.example.refazendoapi.config.SwaggerConfig;
import com.example.refazendoapi.model.Comentario;
import com.example.refazendoapi.model.Local;
import com.example.refazendoapi.model.Response;
import com.example.refazendoapi.service.ComentarioService;
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
@RequestMapping(path = "comentario")
@Api(tags = {SwaggerConfig.COMENTARIO})
public class ComentarioController {
    @Autowired
    private ComentarioService comentarioService;

    @GetMapping
    @ApiOperation(value = "Listar Comentarios")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response<Iterable<Comentario>>> listarComentarios(){
        Response<Iterable<Comentario>> response = new Response<>();
        response.setData(comentarioService.listarComentarios());
        response.setTimeStamp(LocalDateTime.now());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ComentarioController.class)
                .criarComentario(null)).withRel("POST"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ComentarioController.class)
                .listarComentarios()).withSelfRel());
        for (Comentario comentario: response.getData()) {
            comentario.add(linkTo(WebMvcLinkBuilder.methodOn(ComentarioController.class)
                    .buscarComentario(comentario.getIdComentario())).withRel("GET"));
            comentario.add(linkTo(WebMvcLinkBuilder.methodOn(ComentarioController.class)
                    .atualizarComentário(comentario.getIdComentario(), comentario)).withRel("UPDATE"));
            comentario.add(linkTo(WebMvcLinkBuilder.methodOn(ComentarioController.class)
                    .deletarComentário(comentario.getIdComentario())).withRel("DELETE"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar Comentario")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Response<Comentario>> buscarComentario(@PathVariable("id") UUID id){
        Response<Comentario> response = new Response<>();
        response.setData(comentarioService.buscarComentario(id));
        response.setTimeStamp(LocalDateTime.now());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ComentarioController.class)
                .criarComentario(null)).withRel("CREATE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ComentarioController.class)
                .listarComentarios()).withRel("ALL"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ComentarioController.class)
                .buscarComentario(response.getData().getIdComentario())).withSelfRel());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ComentarioController.class)
                .deletarComentário(response.getData().getIdComentario())).withRel("DELETE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ComentarioController.class)
                .atualizarComentário(response.getData().getIdComentario(), response.getData())).withRel("UPDATE"));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    @ApiOperation(value = "Criar Comentário")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Response<Comentario>> criarComentario(@Valid @RequestBody Comentario comentario){
        Response<Comentario> response = new Response<>();
        response.setData(comentarioService.criarComentario(comentario));
        response.setTimeStamp(LocalDateTime.now());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ComentarioController.class)
                .criarComentario(null)).withSelfRel());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ComentarioController.class)
                .listarComentarios()).withRel("ALL"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ComentarioController.class)
                .buscarComentario(response.getData().getIdComentario())).withRel("GET"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ComentarioController.class)
                .deletarComentário(response.getData().getIdComentario())).withRel("DELETE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ComentarioController.class)
                .atualizarComentário(response.getData().getIdComentario(), response.getData())).withRel("UPDATE"));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar Comentário")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Response<Comentario>> atualizarComentário(@PathVariable("id") UUID id, @Valid @RequestBody Comentario comentario){
        Response<Comentario> response = new Response<>();
        response.setData(comentarioService.atualizarComentario(id, comentario));
        response.setTimeStamp(LocalDateTime.now());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ComentarioController.class)
                .criarComentario(null)).withRel("CREATE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ComentarioController.class)
                .listarComentarios()).withRel("ALL"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ComentarioController.class)
                .buscarComentario(response.getData().getIdComentario())).withRel("GET"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ComentarioController.class)
                .deletarComentário(response.getData().getIdComentario())).withRel("DELETE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ComentarioController.class)
                .atualizarComentário(response.getData().getIdComentario(), response.getData())).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar Comentário")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Response<Comentario>> deletarComentário(@PathVariable("id") UUID id){
        Response<Comentario> response = new Response<>();
        response.setData(comentarioService.deletarComentario(id));
        response.setTimeStamp(LocalDateTime.now());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ComentarioController.class)
                .criarComentario(null)).withRel("CREATE"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ComentarioController.class)
                .listarComentarios()).withRel("ALL"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ComentarioController.class)
                .buscarComentario(response.getData().getIdComentario())).withRel("GET"));
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ComentarioController.class)
                .deletarComentário(response.getData().getIdComentario())).withSelfRel());
        response.add(linkTo(WebMvcLinkBuilder.methodOn(ComentarioController.class)
                .atualizarComentário(response.getData().getIdComentario(), response.getData())).withRel("DELETAR"));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

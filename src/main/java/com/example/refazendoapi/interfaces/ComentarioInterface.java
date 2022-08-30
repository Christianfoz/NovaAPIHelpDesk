package com.example.refazendoapi.interfaces;

import com.example.refazendoapi.model.Comentario;

import java.util.UUID;

public interface ComentarioInterface {
    Comentario buscarComentario(UUID id);
    Iterable<Comentario> listarComentarios();
    Comentario criarComentario(Comentario comentario);
    Comentario atualizarComentario(UUID id, Comentario comentario);
    Comentario deletarComentario(UUID id);
}

package com.example.refazendoapi.interfaces;

import com.example.refazendoapi.model.Comentario;

public interface ComentarioInterface {
    Comentario buscarComentario(int id);
    Iterable<Comentario> listarComentarios();
    Comentario criarComentario(Comentario comentario);
    Comentario atualizarComentario(int id, Comentario comentario);
    Comentario deletarComentario(int id);
}

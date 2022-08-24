package com.example.refazendoapi.service;

import com.example.refazendoapi.exception.ComentarioException;
import com.example.refazendoapi.interfaces.ComentarioInterface;
import com.example.refazendoapi.model.Comentario;
import com.example.refazendoapi.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ComentarioService implements ComentarioInterface {
    @Autowired
    private ComentarioRepository comentarioRepository;

    @Override
    public Comentario buscarComentario(int id) {
        try{
            Optional<Comentario> comentario = comentarioRepository.findById(id);
            if(comentario.isPresent()){
                return comentario.get();
            }
            else{
                throw new ComentarioException(HttpStatus.NOT_FOUND, "Comentário não encontrado");
            }
        }catch (ComentarioException c){
            throw c;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Iterable<Comentario> listarComentarios() {
        try{
            Iterable<Comentario> comentarios = comentarioRepository.findAll();
            return comentarios;
        }catch (ComentarioException c){
            throw c;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Comentario criarComentario(Comentario comentario) {
        try{
            comentarioRepository.save(comentario);
            return comentario;
        }catch (ComentarioException c){
            throw c;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Comentario atualizarComentario(int id, Comentario comentario) {
        try{
            Optional<Comentario> comentario1 = comentarioRepository.findById(comentario.getIdComentario());
            if(comentario1.isPresent()){
                if(id == comentario.getIdComentario()){
                    return comentarioRepository.save(comentario);
                }
                else{
                    throw new ComentarioException(HttpStatus.NOT_FOUND, "Erro ao atualizar");
                }
            }
            else{
                throw new ComentarioException(HttpStatus.NOT_FOUND, "Comentário não encontrado");
            }
        }catch (ComentarioException c){
            throw c;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Comentario deletarComentario(int id) {
        try{
            Optional<Comentario> comentario1 = comentarioRepository.findById(id);
            if(comentario1.isPresent()){
                comentarioRepository.delete(comentario1.get());
                return comentario1.get();
            }
            else{
                throw new ComentarioException(HttpStatus.NOT_FOUND, "Comentário não encontrado");
            }
        }catch (ComentarioException c){
            throw c;
        }catch (Exception e){
            throw e;
        }
    }
}

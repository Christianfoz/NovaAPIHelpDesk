package com.example.refazendoapi.repository;

import com.example.refazendoapi.model.Comentario;
import org.springframework.data.repository.CrudRepository;

public interface ComentarioRepository extends CrudRepository<Comentario, Integer> {
}

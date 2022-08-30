package com.example.refazendoapi.repository;

import com.example.refazendoapi.model.Comentario;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ComentarioRepository extends CrudRepository<Comentario, UUID> {
}

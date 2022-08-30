package com.example.refazendoapi.repository;

import com.example.refazendoapi.model.Chamado;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ChamadoRepository extends CrudRepository<Chamado, UUID> {
}

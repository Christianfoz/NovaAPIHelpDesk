package com.example.refazendoapi.repository;

import com.example.refazendoapi.model.Situacao;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SituacaoRepository extends CrudRepository<Situacao, UUID> {
}

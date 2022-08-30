package com.example.refazendoapi.repository;

import com.example.refazendoapi.model.TipoPessoa;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TipoPessoaRepository extends CrudRepository<TipoPessoa, UUID> {
}

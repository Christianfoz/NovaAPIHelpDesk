package com.example.refazendoapi.repository;

import com.example.refazendoapi.model.Pessoa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface PessoaRepository extends CrudRepository<Pessoa, UUID> {
    @Query("SELECT p FROM Pessoa p JOIN FETCH p.tipoPessoa WHERE p.email = :email")
    Pessoa findByEmail(@Param("email") String email);
}

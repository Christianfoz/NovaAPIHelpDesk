package com.example.refazendoapi.interfaces;

import com.example.refazendoapi.model.Pessoa;

import java.util.UUID;

public interface PessoaInterface {
    Pessoa criarPessoa(Pessoa pessoa);
    Iterable<Pessoa> listarPessoas();
    Pessoa buscarPorId(UUID id);
    Pessoa atualizarPessoa(UUID id, Pessoa pessoa);
    Pessoa deletarPessoa(UUID id);
}

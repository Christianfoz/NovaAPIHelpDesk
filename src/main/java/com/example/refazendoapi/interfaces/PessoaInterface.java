package com.example.refazendoapi.interfaces;

import com.example.refazendoapi.model.Pessoa;

public interface PessoaInterface {
    Pessoa criarPessoa(Pessoa pessoa);
    Iterable<Pessoa> listarPessoas();
    Pessoa buscarPorId(int id);
    Pessoa atualizarPessoa(int id, Pessoa pessoa);
    Pessoa deletarPessoa(int id);
}

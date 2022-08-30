package com.example.refazendoapi.interfaces;

import com.example.refazendoapi.model.TipoPessoa;

import java.util.UUID;

public interface TipoPessoaInterface {
    TipoPessoa buscarTipo(UUID id);
    Iterable<TipoPessoa> listarTipos();
    TipoPessoa criarTipo(TipoPessoa tipoPessoa);
    TipoPessoa atualizarTipo(UUID id, TipoPessoa tipoPessoa);
    TipoPessoa deletarTipo(UUID id);
}

package com.example.refazendoapi.interfaces;

import com.example.refazendoapi.model.TipoPessoa;

public interface TipoPessoaInterface {
    TipoPessoa buscarTipo(int id);
    Iterable<TipoPessoa> listarTipos();
    TipoPessoa criarTipo(TipoPessoa tipoPessoa);
    TipoPessoa atualizarTipo(int id, TipoPessoa tipoPessoa);
    TipoPessoa deletarTipo(int id);
}

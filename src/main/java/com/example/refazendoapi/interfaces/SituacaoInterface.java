package com.example.refazendoapi.interfaces;

import com.example.refazendoapi.model.Situacao;

public interface SituacaoInterface {

    Iterable<Situacao> listarSituacoes();
    Situacao buscarSituacao(int id);
    Situacao criarSituacao(Situacao situacao);
    Situacao atualizarSituacao(int id, Situacao situacao);
    Situacao deletarSituacao(int id);
}

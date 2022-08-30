package com.example.refazendoapi.interfaces;

import com.example.refazendoapi.model.Situacao;

import java.util.UUID;

public interface SituacaoInterface {

    Iterable<Situacao> listarSituacoes();
    Situacao buscarSituacao(UUID id);
    Situacao criarSituacao(Situacao situacao);
    Situacao atualizarSituacao(UUID id, Situacao situacao);
    Situacao deletarSituacao(UUID id);
}

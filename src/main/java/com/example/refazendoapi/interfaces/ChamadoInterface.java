package com.example.refazendoapi.interfaces;

import com.example.refazendoapi.model.Chamado;

import java.util.UUID;

public interface ChamadoInterface {
    Chamado buscarChamado(UUID id);
    Iterable<Chamado> listarChamados();
    Chamado criarChamado(Chamado chamado);
    Chamado atualizarChamado(UUID id, Chamado chamado);
    Chamado deletarChamado(UUID id);
}

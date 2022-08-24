package com.example.refazendoapi.interfaces;

import com.example.refazendoapi.model.Chamado;

public interface ChamadoInterface {
    Chamado buscarChamado(int id);
    Iterable<Chamado> listarChamados();
    Chamado criarChamado(Chamado chamado);
    Chamado atualizarChamado(int id, Chamado chamado);
    Chamado deletarChamado(int id);
}

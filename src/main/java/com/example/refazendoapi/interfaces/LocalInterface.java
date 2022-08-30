package com.example.refazendoapi.interfaces;

import com.example.refazendoapi.model.Local;

import java.util.UUID;

public interface LocalInterface {
    Local criarLocal(Local local);
    Local buscarPorId(UUID id);
    Iterable<Local> listarLocais();
    Local editarLocal(UUID id, Local local);
    Local deletarLocal(UUID id);
}

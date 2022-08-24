package com.example.refazendoapi.interfaces;

import com.example.refazendoapi.model.Local;

public interface LocalInterface {
    Local criarLocal(Local local);
    Local buscarPorId(int id);
    Iterable<Local> listarLocais();
    Local editarLocal(int id, Local local);
    Local deletarLocal(int id);
}

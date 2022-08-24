package com.example.refazendoapi.service;

import com.example.refazendoapi.exception.ChamadoException;
import com.example.refazendoapi.interfaces.ChamadoInterface;
import com.example.refazendoapi.model.Chamado;
import com.example.refazendoapi.repository.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChamadoService implements ChamadoInterface {
    @Autowired
    private ChamadoRepository chamadoRepository;
    @Override
    public Chamado buscarChamado(int id) {
        try{
            Optional<Chamado> chamado = chamadoRepository.findById(id);
            if(chamado.isPresent()){
                return chamado.get();
            }
            else{
                throw new ChamadoException(HttpStatus.NOT_FOUND,"Chamado não existente");
            }
        }catch (ChamadoException c){
            throw c;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Iterable<Chamado> listarChamados() {
        try{
            Iterable<Chamado> chamados = chamadoRepository.findAll();
            return chamados;
        }catch (ChamadoException c){
            throw c;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Chamado criarChamado(Chamado chamado) {
        try{
            chamadoRepository.save(chamado);
            return chamado;
        }catch (ChamadoException c){
            throw c;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Chamado atualizarChamado(int id, Chamado chamado) {
        try{
            Optional<Chamado> chamado1 = chamadoRepository.findById(chamado.getIdChamado());
            if(chamado1.isPresent()){
                if(id == chamado.getIdChamado()){
                    chamadoRepository.save(chamado);
                    return chamado;
                }
                else{
                    throw new ChamadoException(HttpStatus.NOT_FOUND, "Erro ao atualizar");
                }
            }
            else{
                throw new ChamadoException(HttpStatus.NOT_FOUND, "Chamado não encontrado");
            }
        }catch (ChamadoException c){
            throw c;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Chamado deletarChamado(int id) {
        try{
            Optional<Chamado> chamado1 = chamadoRepository.findById(id);
            if(chamado1.isPresent()){
                chamadoRepository.delete(chamado1.get());
                return chamado1.get();
            }
            else{
                throw new ChamadoException(HttpStatus.NOT_FOUND, "Chamado não encontrado");
            }
        }catch (ChamadoException c){
            throw c;
        }catch (Exception e){
            throw e;
        }
    }
}

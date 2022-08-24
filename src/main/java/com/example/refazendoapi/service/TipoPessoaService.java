package com.example.refazendoapi.service;

import com.example.refazendoapi.exception.TipoPessoaException;
import com.example.refazendoapi.interfaces.TipoPessoaInterface;
import com.example.refazendoapi.model.TipoPessoa;
import com.example.refazendoapi.repository.TipoPessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipoPessoaService implements TipoPessoaInterface{
    @Autowired
    private TipoPessoaRepository tipoPessoaRepository;

    @Override
    public TipoPessoa buscarTipo(int id) {
        try{
            Optional<TipoPessoa> tipoPessoa = tipoPessoaRepository.findById(id);
            if(tipoPessoa.isPresent()){
                return tipoPessoa.get();
            }
            else{
                throw new TipoPessoaException(HttpStatus.NOT_FOUND, "Tipo de pessoa não encontrado");
            }
        }catch (TipoPessoaException t){
            throw t;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Iterable<TipoPessoa> listarTipos() {
        try{
            return tipoPessoaRepository.findAll();
        }catch (TipoPessoaException t){
            throw t;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public TipoPessoa criarTipo(TipoPessoa tipoPessoa) {
        try{
           return  tipoPessoaRepository.save(tipoPessoa);
        }catch (TipoPessoaException t){
            throw t;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public TipoPessoa atualizarTipo(int id, TipoPessoa tipoPessoa) {
        try{
            Optional<TipoPessoa> tipoPessoa1 = tipoPessoaRepository.findById(tipoPessoa.getIdTipo());
            if(tipoPessoa1.isPresent()){
                if(id == tipoPessoa.getIdTipo()){
                    return tipoPessoaRepository.save(tipoPessoa);
                }
                else{
                    throw new TipoPessoaException(HttpStatus.NOT_FOUND, "Erro ao atualizar");
                }
            }
            else{
                throw new TipoPessoaException(HttpStatus.NOT_FOUND, "Tipo de Pessoa não encontrado");
            }
        }catch (TipoPessoaException t){
            throw t;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public TipoPessoa deletarTipo(int id) {
        try{
            Optional<TipoPessoa> tipoPessoa1 = tipoPessoaRepository.findById(id);
            if(tipoPessoa1.isPresent()){
                tipoPessoaRepository.delete(tipoPessoa1.get());
                return tipoPessoa1.get();
            }
            else{
                throw new TipoPessoaException(HttpStatus.NOT_FOUND, "Tipo de Pessoa não encontrado");
            }
        }catch (TipoPessoaException t){
            throw t;
        }catch (Exception e){
            throw e;
        }
    }
}

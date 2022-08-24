package com.example.refazendoapi.service;

import com.example.refazendoapi.exception.SituacaoException;
import com.example.refazendoapi.interfaces.SituacaoInterface;
import com.example.refazendoapi.model.Situacao;
import com.example.refazendoapi.repository.SituacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SituacaoService implements SituacaoInterface {
    @Autowired
    private SituacaoRepository situacaoRepository;

    @Override
    public Iterable<Situacao> listarSituacoes() {
        try{
            return situacaoRepository.findAll();
        }catch (SituacaoException s){
            throw s;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Situacao buscarSituacao(int id) {
        try{
            Optional<Situacao> situacao = situacaoRepository.findById(id);
            if(situacao.isPresent()){
                return situacao.get();
            }else{
                throw new SituacaoException(HttpStatus.NOT_FOUND, "Situacao não encontrada");
            }
        } catch (SituacaoException s){
            throw s;
        } catch (Exception e){
            throw e;
        }
    }

    @Override
    public Situacao criarSituacao(Situacao situacao) {
        try{
            return situacaoRepository.save(situacao);
        }catch (SituacaoException s){
            throw s;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Situacao atualizarSituacao(int id, Situacao situacao) {
        try{
            if(id == situacao.getIdSituacao()){
                Optional<Situacao> situacao1 = situacaoRepository.findById(id);
                if(situacao1.isPresent()){
                    return situacaoRepository.save(situacao);
                }
                else{
                    throw new SituacaoException(HttpStatus.NOT_FOUND, "Situacao nao encontrada");
                }
            }
            else{
                throw new SituacaoException(HttpStatus.BAD_REQUEST, "Erro ao atualizar");
            }
        } catch (SituacaoException s){
            throw s;
        } catch (Exception e){
            throw e;
        }
    }

    @Override
    public Situacao deletarSituacao(int id) {
        try{
            Optional<Situacao> situacao = situacaoRepository.findById(id);
            if(situacao.isPresent()){
                situacaoRepository.delete(situacao.get());
                return situacao.get();
            }
            else{
                throw new SituacaoException(HttpStatus.NOT_FOUND, "Situacao nao encontrada");
            }
        }catch (SituacaoException s){
            throw s;
        }catch (Exception e){
            throw e;
        }
    }
}

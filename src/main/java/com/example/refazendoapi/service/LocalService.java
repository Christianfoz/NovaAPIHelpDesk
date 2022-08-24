package com.example.refazendoapi.service;

import com.example.refazendoapi.exception.LocalException;
import com.example.refazendoapi.interfaces.LocalInterface;
import com.example.refazendoapi.model.Local;
import com.example.refazendoapi.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocalService implements LocalInterface {
    @Autowired
    private LocalRepository localRepository;

    @Override
    public Local criarLocal(Local local) {
        try{
            localRepository.save(local);
            return local;
        }catch (LocalException l){
            throw l;
        } catch (Exception e){
            throw e;
        }
    }

    @Override
    public Local buscarPorId(int id) {
        try{
            Optional<Local> local = localRepository.findById(id);
            if(local.isPresent()){
                return local.get();
            }else{
                throw new LocalException(HttpStatus.NOT_FOUND,"Local não encontrado");
            }

        }catch (LocalException l){
            throw l;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Iterable<Local> listarLocais() {
        try{
            Iterable<Local> locais = localRepository.findAll();
            return locais;
        }catch (LocalException l){
            throw l;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Local editarLocal(int id, Local local) {
        try{
            Optional<Local> local1 = localRepository.findById(id);
            if(local1.isPresent()){
                localRepository.save(local);
                return local;
            }
            else{
                throw new LocalException(HttpStatus.NOT_FOUND,"Local não encontrado");
            }
        }catch (LocalException l){
            throw l;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Local deletarLocal(int id) {
        try{
            Optional<Local> local = localRepository.findById(id);
            if(local.isPresent()){
                localRepository.delete(local.get());
                return local.get();
            }
            else{
                throw new LocalException(HttpStatus.NOT_FOUND,"Local não encontrado");
            }
        }catch (LocalException l){
            throw l;
        }catch (Exception e){
            throw e;
        }
    }
}

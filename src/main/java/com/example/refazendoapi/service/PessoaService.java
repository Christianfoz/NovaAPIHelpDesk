package com.example.refazendoapi.service;

import com.example.refazendoapi.exception.PessoaException;
import com.example.refazendoapi.interfaces.PessoaInterface;
import com.example.refazendoapi.model.Pessoa;
import com.example.refazendoapi.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService implements PessoaInterface {
    @Autowired
    private PessoaRepository pessoaRepository;

    private BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    public Pessoa criarPessoa(Pessoa pessoa) {
        try{
            pessoa.setSenha(passwordEncoder().encode(pessoa.getSenha()));
            pessoaRepository.save(pessoa);
            return pessoa;
        }catch (PessoaException p){
            throw p;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Iterable<Pessoa> listarPessoas() {
        try{
            Iterable<Pessoa> pessoas = pessoaRepository.findAll();
            return pessoas;
        }catch (PessoaException p){
            throw p;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Pessoa buscarPorId(int id) {
        try{
            Optional<Pessoa> pessoa = pessoaRepository.findById(id);
            if(pessoa.isPresent()){
                return pessoa.get();
            }
            else{
                throw new PessoaException(HttpStatus.NOT_FOUND, "Pessoa não encontrada");
            }
        }catch (PessoaException p){
            throw p;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Pessoa atualizarPessoa(int id, Pessoa pessoa) {
        try{
            Optional<Pessoa> pessoa1 = pessoaRepository.findById(id);
            if(pessoa1.isPresent()){
                if(id == pessoa.getIdPessoa()){
                    pessoaRepository.save(pessoa);
                    return pessoa;
                }
                throw new PessoaException(HttpStatus.BAD_REQUEST, "Errp ao atualizar");

            }
            else{
                throw new PessoaException(HttpStatus.NOT_FOUND, "Pessoa não encontrada");
            }
        }catch (PessoaException p){
            throw p;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Pessoa deletarPessoa(int id) {
        try{
            Optional<Pessoa> pessoa = pessoaRepository.findById(id);
            if(pessoa.isPresent()){
                pessoaRepository.delete(pessoa.get());
                return pessoa.get();
            }
            else{
                throw new PessoaException(HttpStatus.NOT_FOUND, "Pessoa não encontrada");
            }
        }catch (PessoaException p){
            throw p;
        }catch (Exception e){
            throw e;
        }
    }
}

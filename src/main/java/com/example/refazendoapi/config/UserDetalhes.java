package com.example.refazendoapi.config;

import com.example.refazendoapi.model.Pessoa;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetalhes implements UserDetails {
    private String nome;
    private String password;
    private Collection<? extends GrantedAuthority> autorithies;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return autorithies;
    }

    public UserDetalhes(Pessoa pessoa){
        this.nome = pessoa.getNome();
        this.password = pessoa.getSenha();
        List<SimpleGrantedAuthority> autorithies = new ArrayList<>();
        autorithies = pessoa.getTipoPessoa().stream().map(tipo ->{
           return new SimpleGrantedAuthority("ROLE_".concat(tipo.getNomeTipo()));})
                .collect(Collectors.toList());
        this.autorithies = autorithies;

    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

package com.example.refazendoapi.config;

import com.example.refazendoapi.model.Pessoa;
import com.example.refazendoapi.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetails implements UserDetailsService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Pessoa pessoa = pessoaRepository.findByEmail(email);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        pessoa.getTipoPessoa().forEach(
                role -> authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getNomeTipo())))
        );
        return new UserDetalhes(pessoa);
    }
}

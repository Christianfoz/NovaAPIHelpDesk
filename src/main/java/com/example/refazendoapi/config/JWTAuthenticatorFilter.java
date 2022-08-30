package com.example.refazendoapi.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.refazendoapi.model.Pessoa;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JWTAuthenticatorFilter extends UsernamePasswordAuthenticationFilter {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Value("${JWT_SECRET}")
    private String jwtSecret;
    @Value("${SESSION_TIME}")
    private int sessionTime;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            Pessoa pessoa = new ObjectMapper().readValue(request.getInputStream(), Pessoa.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    pessoa.getEmail(), pessoa.getSenha(), pessoa.getTipoPessoa()
            ));
        } catch (IOException e) {
            throw new RuntimeException("Falha ao autenticar usuário", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetalhes userDetalhes = (UserDetalhes) authResult.getPrincipal();
        String token = JWT.create()
                .withSubject(userDetalhes.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + sessionTime))
                .sign(Algorithm.HMAC512(jwtSecret));
        response.getWriter().write(token);
        response.getWriter().flush();
    }
}

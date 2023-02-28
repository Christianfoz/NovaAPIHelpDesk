package com.example.refazendoapi.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtValidatorFilter extends OncePerRequestFilter {

    @Value("${JWT_SECRET}")
    private static String jwtSecret;
    public JwtValidatorFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String atributo = request.getHeader("Authorization");
        if(atributo == null){
            chain.doFilter(request,response);
        }
        if(!atributo.startsWith("Bearer ")){
            chain.doFilter(request,response);
        }
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token){
        String usuario = JWT.require(Algorithm.HMAC512(jwtSecret)).build().verify(token).getSubject();
        if(usuario.isEmpty()){
            return null;
        }

        return new UsernamePasswordAuthenticationToken()
    }
}

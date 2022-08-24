package com.example.refazendoapi.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public static final String CHAMADO = "Métodos de Chamado";
    public static final String COMENTARIO = "Métodos de Comentário";
    public static final String LOCAL = "Métodos de Local";
    public static final String PESSOA = "Métodos de Pessoa";
    public static final String SITUACAO = "Métodos de Situação";
    public static final String TIPO_PESSOA = "Métodos de Tipo de Pessoa";
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.refazendoapi.controller")).
                paths(PathSelectors.any())
                .build()
                .tags(new Tag(CHAMADO, "Controladora de Chamados"),
                        new Tag(COMENTARIO, "Controladora de Comentários"),
                        new Tag(LOCAL, "Controladora de Locais"),
                        new Tag(PESSOA, "Controladora de Pessoas"),
                        new Tag(SITUACAO, "Controladora de Situações"),
                        new Tag(TIPO_PESSOA, "Controladora de Tipos de Pessoa"));
    }
}

package com.example.refazendoapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
public class TipoPessoa extends RepresentationModel<TipoPessoa> implements GrantedAuthority{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private Integer idTipo;
    @NotBlank(message = "Tipo de Pessoa não pode ser nulo")
    @Column(length = 15)
    @Size(min = 5, max = 15, message = "Campo tipo deve ter no mínimo {min} e no máximo {max} caracteres")
    private String nomeTipo;

    @Override
    public String getAuthority() {
        return nomeTipo;
    }
}

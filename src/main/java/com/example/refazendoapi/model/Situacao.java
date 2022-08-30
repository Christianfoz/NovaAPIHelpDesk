package com.example.refazendoapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "situacao")
public class Situacao extends RepresentationModel<Situacao> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_situacao")
    private UUID idSituacao;
    @NotBlank(message = "Situação não pode ser nula")
    @Size(min = 5, max = 20, message = "Campo situação deve ter no mínimo {min} e no máximo {max} caracteres\"")
    private String nomeSituacao;
}

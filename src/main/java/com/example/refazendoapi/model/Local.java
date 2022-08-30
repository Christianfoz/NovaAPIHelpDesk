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
@Table(name = "local")
public class Local extends RepresentationModel<Local> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_local")
    private UUID idLocal;
    @NotBlank(message = "Local não pode ser nulo")
    @Column(length = 60)
    @Size(min = 3, max = 60,message = "Campo local deve ter no mínimo {min} e no máximo {max} caracteres")
    private String nomeLocal;
}

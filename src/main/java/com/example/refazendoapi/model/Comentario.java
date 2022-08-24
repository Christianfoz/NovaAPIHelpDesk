package com.example.refazendoapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "comentario")
public class Comentario extends RepresentationModel<Comentario> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comentario")
    private Integer idComentario;
    @NotBlank(message = "Insira um comentário válido")
    @Size(min = 2, max = 120, message = "Campo comentário deve ter no mínimo {min} e no máximo {max} caracteres")
    private String comentario;
    @NotBlank(message = "Insira o criador do  comentário")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_criador")
    private Pessoa criadorComentario;
    @NotBlank(message = "Insira o chamado onde o comentário será feito")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_chamado")
    private Chamado chamado;
    private ZonedDateTime dataComentario;
}

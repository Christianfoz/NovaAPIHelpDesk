package com.example.refazendoapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "chamado")
public class Chamado extends RepresentationModel<Chamado> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_chamado")
    private UUID idChamado;
    @NotBlank(message = "Título não pode ser vazio")
    @Column(length = 40)
    @Size(min = 5, max = 40, message = "Campo titulo deve ter no mínimo  {min} e no máximo {max} caracteres")
    private String titulo;
    @NotBlank(message = "Descrição não pode ser vazia")
    @Column(length = 120)
    @Size(min = 5, max = 120, message = "Campo descrição deve ter no mínimo  {min} e no máximo {max} caracteres")
    private String descricao;
    @Size(min = 5, max = 120, message = "Campo solução deve ter no mínimo  {min} e no máximo {max} caracteres")
    private String solucao;
    private String imagem;
    private ZonedDateTime dataInicio;
    private ZonedDateTime dataFinalizacao;
    @NotEmpty(message = "É necessária Situação")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_situacao")
    private Situacao situacao;
    @NotEmpty(message = "É necessário Cliente")
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Pessoa cliente;
    @ManyToOne
    @JoinColumn(name = "id_tecnico")
    private Pessoa tecnico;
    @NotBlank(message = "É necessário Local")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_local")
    private Local local;
    private Boolean status;
}

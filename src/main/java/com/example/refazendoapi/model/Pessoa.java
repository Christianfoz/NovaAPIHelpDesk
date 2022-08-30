package com.example.refazendoapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class Pessoa extends RepresentationModel<Pessoa>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa")
    private UUID idPessoa;
    @NotBlank(message = "Insira o nome")
    @Column(length = 20)
    @Size(min = 3, max = 20, message = "Campo nome deve ter no mínimo {min} e no máximo {max} caracteres")
    private String nome;
    @NotBlank(message = "Insira o sobrenome")
    @Column(length = 50)
    @Size(min = 3, max = 50, message = "Campo sobrenome deve ter no mínimo {min} e no máximo {max} caracteres")
    private String sobrenome;
    @CPF(message = "Insira um cpf válido")
    @NotBlank(message = "Insira um CPF")
    @Size( min = 14, max = 14, message = "Campo cpf deve ter no máximo {max} caracteres")
    @Column(unique = true)
    private String cpf;
    @NotBlank(message = "Insira o telefone")
    @Size(min = 10, max = 14, message = "Campo telefone deve ter no máximo {max} caracteres")
    private String telefone;
    @Email(message = "Insira um email válido")
    @Column(unique = true)
    @NotBlank(message = "Insira um email")
    @Size(min = 10, max = 50, message = "Campo email deve ter no mínimo {min} e no máximo {max} caracteres")
    private String email;
    @NotBlank(message = "Insira uma senha válida")
    @Column(columnDefinition = "VARCHAR(200) NOT NULL")
    private String senha;
    private Boolean validado;
    private String foto;
    @ManyToMany
    @JoinColumn(name = "id_tipo")
    private Collection<TipoPessoa> tipoPessoa;
}

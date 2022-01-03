package br.com.longato.consultaemprestimo.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Builder
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nome;
    private String cpf;
    private String rg;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private Integer cep;
    private Double renda;
    private String email;
    private String senha;


}

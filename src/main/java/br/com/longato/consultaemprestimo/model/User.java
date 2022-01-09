package br.com.longato.consultaemprestimo.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Builder
@Entity
public class User{

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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tab_user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role_id")
    @Builder.Default
    private List<String> roles = new ArrayList<>();

}

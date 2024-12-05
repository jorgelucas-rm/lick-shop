package senac.ads.projeto_integrador_iv.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;

    private String cpf;

    private String nascimento;

    @ManyToMany
    private List<Endereco> endereco = new ArrayList<>();

    @OneToOne
    private Conta conta;

    @OneToMany
    private List<Produto> carrinho;
}

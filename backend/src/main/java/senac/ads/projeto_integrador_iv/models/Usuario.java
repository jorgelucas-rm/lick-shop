package senac.ads.projeto_integrador_iv.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private String nome;

    @Id
    private Long cpf;

    private String nascimento;

    @ManyToMany
    private List<Endereco> endereco = new ArrayList<>();

    @OneToOne
    private Conta conta;
}

package senac.ads.projeto_integrador_iv.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @OneToOne
    private Usuario dono;

    private String endereco;

    private Situacao situacao;

    @ManyToMany
    private List<Produto> produtos = new ArrayList<>();

    private double valorTotal;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;
}


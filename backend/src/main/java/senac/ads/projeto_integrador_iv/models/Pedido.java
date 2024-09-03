package senac.ads.projeto_integrador_iv.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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

    private Usuario dono;

    private String endereco;

    private String situacao;

    @ManyToMany
    private List<Produto> produtos = new ArrayList<>();

    private double valorTotal;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;
}


package senac.ads.projeto_integrador_iv.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carrinho {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @OneToOne
    private Usuario dono;

    @ManyToMany
    private List<Produto> produtos = new ArrayList<>();

    private double valorTotal;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;
}

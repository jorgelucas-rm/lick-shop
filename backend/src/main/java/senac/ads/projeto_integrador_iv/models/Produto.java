package senac.ads.projeto_integrador_iv.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;

    private List<String> imagemList;

    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    private Marca marca;

    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    private Categoria categoria;

    private String descricaoCurta;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String descricaoDetalhada;

    private double valorCusto;

    private double valorVenda;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;
}

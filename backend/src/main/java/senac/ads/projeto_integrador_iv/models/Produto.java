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

    @Lob
    @ElementCollection
    @CollectionTable(name = "produto_imagens", joinColumns = @JoinColumn(name = "produto_id"))
    @Column(name = "imagem", columnDefinition = "LONGBLOB")
    private List<byte[]> imagemList;

    @ManyToOne
    private Marca marca;

    @ManyToOne
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

package senac.ads.projeto_integrador_iv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoTO {

    private String nome;

    private UUID marcaId;

    private UUID categoriaId;

    private String descricaoCurta;

    private String descricaoDetalhada;

    private double valorCusto;

    private double valorVenda;
}

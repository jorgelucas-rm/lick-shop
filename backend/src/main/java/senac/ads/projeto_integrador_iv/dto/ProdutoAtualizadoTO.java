package senac.ads.projeto_integrador_iv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProdutoAtualizadoTO {

    private String nome;

    private String descricaoCurta;

    private String descricaoDetalhada;

    private double valorCusto;

    private double valorVenda;
}

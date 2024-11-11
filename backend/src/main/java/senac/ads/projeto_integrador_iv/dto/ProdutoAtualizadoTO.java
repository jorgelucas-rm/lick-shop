package senac.ads.projeto_integrador_iv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProdutoAtualizadoTO {

    private String nome;

    private List<byte[]> imagens;

    private String descricaoCurta;

    private String descricaoDetalhada;

    private double valorCusto;

    private double valorVenda;
}

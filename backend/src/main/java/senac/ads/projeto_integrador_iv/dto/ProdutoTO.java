package senac.ads.projeto_integrador_iv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ProdutoTO {

    private String nome;

    private List<byte[]> imagens;

    private UUID marcaId;

    private UUID categoriaId;

    private String descricaoCurta;

    private String descricaoDetalhada;

    private double valorCusto;

    private double valorVenda;
}

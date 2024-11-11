package senac.ads.projeto_integrador_iv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import senac.ads.projeto_integrador_iv.models.Carrinho;

import java.util.UUID;

@Service
public class PedidoService {

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private PagamentoService pagamentoService;

    public ResponseEntity gerarPedido(UUID carrinhoId){
        try {
            Carrinho carrinho = carrinhoService.obterCarrinhoPorId(carrinhoId);
            String linkPagamento = pagamentoService.criarLinkDePagamento(carrinho);
            return ResponseEntity.ok(linkPagamento);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao criar link de pagamento");
        }
    }
}

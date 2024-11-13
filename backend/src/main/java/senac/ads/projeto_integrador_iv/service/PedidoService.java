package senac.ads.projeto_integrador_iv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import senac.ads.projeto_integrador_iv.infra.util.JwtUtil;
import senac.ads.projeto_integrador_iv.models.Pedido;
import senac.ads.projeto_integrador_iv.models.Produto;
import senac.ads.projeto_integrador_iv.models.Situacao;
import senac.ads.projeto_integrador_iv.models.Usuario;
import senac.ads.projeto_integrador_iv.repository.PedidoRepository;
import senac.ads.projeto_integrador_iv.repository.UsuarioRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PedidoService {

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public ResponseEntity gerarPedido(String authorizationHeader){
        try {
            Usuario usuario = jwtUtil.extractUserFromJwtToken(authorizationHeader);
            String linkPagamento = pagamentoService.criarLinkDePagamento(usuario);

            double valorTotal = 0;
            for (Produto produto : usuario.getCarrinho()) {
                valorTotal += produto.getValorVenda();
            }

            //gerando pedido
            Pedido pedido = new Pedido();
            pedido.setLinkPagamento(linkPagamento);
            pedido.setDono(usuario);
            pedido.setEndereco(usuario.getEndereco().get(0).getEnderecoCompleto());
            pedido.setSituacao(Situacao.Pendente);
            pedido.setProdutos(usuario.getCarrinho());
            pedido.setValorTotal(valorTotal);
            pedido.setDataCriacao(LocalDateTime.now());
            pedido.setDataAtualizacao(LocalDateTime.now());

            pedidoRepository.save(pedido);

            //zerando carrinho
            usuario.setCarrinho(null);
            usuarioRepository.save(usuario);

            return ResponseEntity.ok(linkPagamento);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao criar link de pagamento");
        }
    }
}

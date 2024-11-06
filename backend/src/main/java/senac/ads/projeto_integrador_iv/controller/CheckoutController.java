package senac.ads.projeto_integrador_iv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import senac.ads.projeto_integrador_iv.service.PedidoService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/checkout")
public class CheckoutController {

    @Autowired
    PedidoService pedidoService;

    @GetMapping("/criar")
    public ResponseEntity<String> criarLinkDePagamento(@RequestParam UUID carrinhoId) {
        return pedidoService.gerarPedido(carrinhoId);
    }
}

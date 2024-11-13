package senac.ads.projeto_integrador_iv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import senac.ads.projeto_integrador_iv.service.PedidoService;

public class CarrinhoCrontroller {

    @Autowired
    PedidoService pedidoService;


    @PostMapping("/gerarPedido")
    public ResponseEntity<String> criarLinkDePagamento(@RequestHeader("Authorization") String authorizationHeader) {
        return pedidoService.gerarPedido(authorizationHeader);
    }
}

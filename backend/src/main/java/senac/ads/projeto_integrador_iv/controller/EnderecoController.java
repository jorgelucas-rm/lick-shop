package senac.ads.projeto_integrador_iv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.ads.projeto_integrador_iv.dto.EnderecoTO;
import senac.ads.projeto_integrador_iv.service.EnderecoService;


@RestController
@RequestMapping("api/v1/endereco")
public class EnderecoController {
    @Autowired
    EnderecoService enderecoService;

    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoTO> buscarEndereco(@PathVariable("cep") String cep){
        return enderecoService.buscarEndereco(cep);
    }
}

package senac.ads.projeto_integrador_iv.adapters;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import senac.ads.projeto_integrador_iv.dto.EnderecoViaCepTO;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepAdapter {
    @GetMapping("/{cep}/json/")
    EnderecoViaCepTO consultarCep(@PathVariable("cep") String cep);
}

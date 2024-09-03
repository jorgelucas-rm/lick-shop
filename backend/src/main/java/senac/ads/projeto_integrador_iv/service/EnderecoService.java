package senac.ads.projeto_integrador_iv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import senac.ads.projeto_integrador_iv.adapters.ViaCepAdapter;
import senac.ads.projeto_integrador_iv.dto.EnderecoTO;
import senac.ads.projeto_integrador_iv.dto.EnderecoViaCepTO;

@Service
public class EnderecoService {
    @Autowired
    private ViaCepAdapter viaCepAdapter;

    public ResponseEntity<EnderecoTO> buscarEndereco(String cep) {
        System.out.println(cep);
        EnderecoViaCepTO enderecoViaCep = viaCepAdapter.consultarCep(cep);

        EnderecoTO endereco = new EnderecoTO(
                enderecoViaCep.getCep(),
                enderecoViaCep.getLogradouro(),
                enderecoViaCep.getBairro(),
                enderecoViaCep.getLocalidade(),
                enderecoViaCep.getEstado());

        return new ResponseEntity<>(endereco, HttpStatus.OK);
    }
}

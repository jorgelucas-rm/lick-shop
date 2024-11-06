package senac.ads.projeto_integrador_iv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import senac.ads.projeto_integrador_iv.adapters.ViaCepAdapter;
import senac.ads.projeto_integrador_iv.dto.EnderecoTO;
import senac.ads.projeto_integrador_iv.dto.EnderecoViaCepTO;
import senac.ads.projeto_integrador_iv.models.Endereco;
import senac.ads.projeto_integrador_iv.repository.EnderecoRepository;

@Service
public class EnderecoService {
    @Autowired
    private ViaCepAdapter viaCepAdapter;

    @Autowired EnderecoRepository enderecoRepository;

    public ResponseEntity<EnderecoTO> buscarEndereco(String cep) {
        EnderecoViaCepTO enderecoViaCep = viaCepAdapter.consultarCep(cep);
        EnderecoTO endereco = new EnderecoTO(
                enderecoViaCep.getCep(),
                enderecoViaCep.getLogradouro(),
                enderecoViaCep.getBairro(),
                enderecoViaCep.getLocalidade(),
                enderecoViaCep.getEstado(),
                null);

        return new ResponseEntity<>(endereco, HttpStatus.OK);
    }

    public Endereco salvarEndereco(EnderecoTO enderecoTO){
        Endereco endereco = new Endereco();
        endereco.setCep(enderecoTO.getCep());
        endereco.setLogradouro(enderecoTO.getLogradouro());
        endereco.setBairro(enderecoTO.getBairro());
        endereco.setCidade(enderecoTO.getCidade());
        endereco.setEstado(enderecoTO.getEstado());
        endereco.setComplemento(enderecoTO.getComplemento());

        enderecoRepository.save(endereco);
        return endereco;
    }
}

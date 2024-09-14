package senac.ads.projeto_integrador_iv.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import senac.ads.projeto_integrador_iv.dto.MarcaTO;
import senac.ads.projeto_integrador_iv.models.Marca;
import senac.ads.projeto_integrador_iv.repository.MarcaRepository;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public ResponseEntity<List<Marca>> buscarTodos(){
        return new ResponseEntity<>(marcaRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Marca> buscarMarcaPorId(UUID id){
        Marca marca = marcaRepository.findById(id).orElse(null);

        if(marca == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(marca, HttpStatus.OK);
    }

    public ResponseEntity<Marca> salvarMarca(MarcaTO novaMarca){
        if(marcaRepository.findByNome(novaMarca.getNome()) != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Marca marca = new Marca();

        marca.setNome(novaMarca.getNome());
        marca.setDataCriacao(LocalDateTime.now());
        marca.setDataAtualizacao(LocalDateTime.now());

        marcaRepository.save(marca);

        return new ResponseEntity<>(marca, HttpStatus.OK);
    }

    public ResponseEntity<Marca> atualizarMarca(UUID id, MarcaTO atualizacaoMarca) {
        Marca marca = marcaRepository.findById(id).orElse(null);

        if(marca == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        marca.setNome(atualizacaoMarca.getNome());
        marca.setDataAtualizacao(LocalDateTime.now());

        marcaRepository.save(marca);
        return new ResponseEntity<>(marca, HttpStatus.OK);
    }

    public ResponseEntity<Marca> deletarMarca(UUID id){
        if(marcaRepository.findById(id).orElse(null) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        marcaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

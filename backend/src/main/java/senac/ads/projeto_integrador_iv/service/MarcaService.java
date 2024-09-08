package senac.ads.projeto_integrador_iv.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public ResponseEntity salvarMarca(Marca novaMarca){

        marcaRepository.save(novaMarca);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity atualizarMarca(UUID id, Marca atualizacaoMarca) {
        Marca marca = marcaRepository.findById(id).orElse(null);

        if(marca == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        marcaRepository.save(atualizacaoMarca);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity deletarMarca(UUID id){
        Marca marca = marcaRepository.findById(id).orElse(null);

        if(marca == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        marcaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}


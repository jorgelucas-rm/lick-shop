package senac.ads.projeto_integrador_iv.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import senac.ads.projeto_integrador_iv.dto.CategoriaTO;
import senac.ads.projeto_integrador_iv.models.Categoria;
import senac.ads.projeto_integrador_iv.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public ResponseEntity<List<Categoria>> buscarTodos(){
        return new ResponseEntity<>(categoriaRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Categoria> buscarCategoriaPorId(UUID id){
        Categoria categoria = categoriaRepository.findById(id).orElse(null);

        if(categoria == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    public ResponseEntity salvarCategoria(CategoriaTO novaCategoria){
        Categoria categoria = new Categoria();
        categoria.setNome(novaCategoria.getNome());
        categoria.setDataCriacao(LocalDateTime.now());
        categoria.setDataAtualizacao(LocalDateTime.now());

        categoriaRepository.save(categoria);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    public ResponseEntity atualizarCategoria(UUID id, Categoria atualizacaoCategoria) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);

        if(categoria == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoriaRepository.save(atualizacaoCategoria);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity deletarCategoria(UUID id){
        Categoria categoria = categoriaRepository.findById(id).orElse(null);

        if(categoria == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoriaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}


package senac.ads.projeto_integrador_iv.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import senac.ads.projeto_integrador_iv.models.Produto;
import senac.ads.projeto_integrador_iv.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ResponseEntity<List<Produto>> buscarTodos(){
        return new ResponseEntity<>(produtoRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Produto> buscarProdutoPorId(UUID id){
        Produto produto = produtoRepository.findById(id).orElse(null);

        if(produto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    public ResponseEntity salvarProduto(Produto novoProduto){

        produtoRepository.save(novoProduto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity atualizarProduto(UUID id, Produto atualizacaoProduto) {
        Produto produto = produtoRepository.findById(id).orElse(null);

        if(produto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        produtoRepository.save(atualizacaoProduto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity deletarProduto(UUID id){
        Produto produto = produtoRepository.findById(id).orElse(null);

        if(produto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        produtoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

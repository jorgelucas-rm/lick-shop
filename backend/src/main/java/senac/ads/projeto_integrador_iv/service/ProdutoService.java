package senac.ads.projeto_integrador_iv.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import senac.ads.projeto_integrador_iv.dto.ProdutoAtualizadoTO;
import senac.ads.projeto_integrador_iv.dto.ProdutoTO;
import senac.ads.projeto_integrador_iv.models.Produto;
import senac.ads.projeto_integrador_iv.repository.CategoriaRepository;
import senac.ads.projeto_integrador_iv.repository.MarcaRepository;
import senac.ads.projeto_integrador_iv.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

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

    public ResponseEntity<Produto> salvarProduto(ProdutoTO novoProduto){
        if (produtoRepository.findByNome(novoProduto.getNome()) != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Produto produto = new Produto();

        produto.setNome(novoProduto.getNome());
        produto.setDescricaoCurta(novoProduto.getDescricaoCurta());
        produto.setDescricaoDetalhada(novoProduto.getDescricaoDetalhada());
        produto.setValorCusto(novoProduto.getValorCusto());
        produto.setValorVenda(novoProduto.getValorVenda());
        produto.setCategoria(categoriaRepository.findById(novoProduto.getCategoriaId()).orElse(null));
        produto.setMarca(marcaRepository.findById(novoProduto.getMarcaId()).orElse(null));
        produto.setDataCriacao(LocalDateTime.now());
        produto.setDataAtualizacao(LocalDateTime.now());

        produtoRepository.save(produto);
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    public ResponseEntity<Produto> atualizarProduto(UUID id, ProdutoAtualizadoTO atualizacaoProduto) {
        Produto produto = produtoRepository.findById(id).orElse(null);

        if(produto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        produto.setNome(atualizacaoProduto.getNome());
        produto.setDescricaoCurta(atualizacaoProduto.getDescricaoCurta());
        produto.setDescricaoDetalhada(atualizacaoProduto.getDescricaoDetalhada());
        produto.setValorCusto(atualizacaoProduto.getValorCusto());
        produto.setValorVenda(atualizacaoProduto.getValorVenda());
        produto.setDataAtualizacao(LocalDateTime.now());

        produtoRepository.save(produto);
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    public ResponseEntity<Produto> deletarProduto(UUID id){
        if(produtoRepository.findById(id).orElse(null) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        produtoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

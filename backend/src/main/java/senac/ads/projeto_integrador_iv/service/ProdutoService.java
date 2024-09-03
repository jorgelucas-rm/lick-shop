package senac.ads.projeto_integrador_iv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import senac.ads.projeto_integrador_iv.models.Produto;
import senac.ads.projeto_integrador_iv.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> getTodosProdutos(){
        return produtoRepository.findAll();
    }

    public Optional<Produto> getProdutoPorId(int codigo){
        return produtoRepository.findById(codigo);
    }

    public Produto salvarProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    public void deletarProduto(int codigo){
        produtoRepository.deleteById(codigo);
    }
}

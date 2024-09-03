package senac.ads.projeto_integrador_iv.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import senac.ads.projeto_integrador_iv.models.Produto;
import senac.ads.projeto_integrador_iv.service.ProdutoService;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService ProdutoService;

    @GetMapping
    public List<Produto> getTodosProdutos(){
        return ProdutoService.getTodosProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoPorId(@PathVariable int codigo){
        Optional<Produto> Produto = ProdutoService.getProdutoPorId(codigo);
        if (Produto.isPresent()){
            return ResponseEntity.ok(Produto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto){
        return produto.salvarProduto(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable int codigo){
        ProdutoService.deletarProduto(codigo);
        return ResponseEntity.noContent().build();
    }
}

}

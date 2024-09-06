package senac.ads.projeto_integrador_iv.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import senac.ads.projeto_integrador_iv.models.Produto;
import senac.ads.projeto_integrador_iv.service.ProdutoService;

@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> buscarTodos(){
        return produtoService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable UUID id){
        return produtoService.buscarProdutoPorId(id);
    }

    @PostMapping
    public ResponseEntity criarProduto(@RequestBody Produto produto){
        return produtoService.salvarProduto(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarProduto(@PathVariable("id") UUID id, @RequestBody Produto produto){
        return produtoService.atualizarProduto(id, produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarProduto(@PathVariable UUID id){
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}

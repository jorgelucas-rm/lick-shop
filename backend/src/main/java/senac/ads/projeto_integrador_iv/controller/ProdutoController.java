package senac.ads.projeto_integrador_iv.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import senac.ads.projeto_integrador_iv.dto.ProdutoAtualizadoTO;
import senac.ads.projeto_integrador_iv.dto.ProdutoTO;
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
<<<<<<< HEAD
    public ResponseEntity<Produto> criarProduto(@RequestBody ProdutoTO produto){
=======
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto){
>>>>>>> 89154a2 ([Backend]Feat: Alterações Usuario/ Produto/ Endereço Service. Criação UserTO. Alteração Produto Controler)
        return produtoService.salvarProduto(produto);
    }

    @PutMapping("/{id}")
<<<<<<< HEAD
    public ResponseEntity<Produto> atualizarProduto(@PathVariable("id") UUID id, @RequestBody ProdutoAtualizadoTO produto){
=======
    public ResponseEntity<Produto> atualizarProduto(@PathVariable("id") UUID id, @RequestBody Produto produto){
>>>>>>> 89154a2 ([Backend]Feat: Alterações Usuario/ Produto/ Endereço Service. Criação UserTO. Alteração Produto Controler)
        return produtoService.atualizarProduto(id, produto);
    }

    @DeleteMapping("/{id}")
<<<<<<< HEAD
    public ResponseEntity<Produto> deletarProduto(@PathVariable UUID id){
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
=======
    public ResponseEntity<Void> deletarProduto(@PathVariable UUID id){
        return produtoService.deletarProduto(id);
>>>>>>> 89154a2 ([Backend]Feat: Alterações Usuario/ Produto/ Endereço Service. Criação UserTO. Alteração Produto Controler)
    }
}

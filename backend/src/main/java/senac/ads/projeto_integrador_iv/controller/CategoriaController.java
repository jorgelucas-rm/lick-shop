package senac.ads.projeto_integrador_iv.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import senac.ads.projeto_integrador_iv.dto.CategoriaTO;
import senac.ads.projeto_integrador_iv.models.Categoria;
import senac.ads.projeto_integrador_iv.service.CategoriaService;

@RestController
@RequestMapping("/api/v1/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>> buscarTodos(){
        return categoriaService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable UUID id){
        return categoriaService.buscarCategoriaPorId(id);
    }

    @PostMapping
    public ResponseEntity criarCategoria(@RequestBody CategoriaTO categoria){
        return categoriaService.salvarCategoria(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarCategoria(@PathVariable("id") UUID id, @RequestBody Categoria categoria){
        return categoriaService.atualizarCategoria(id, categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarCategoria(@PathVariable UUID id){
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}

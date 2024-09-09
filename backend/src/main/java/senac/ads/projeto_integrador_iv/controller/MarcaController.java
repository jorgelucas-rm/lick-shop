package senac.ads.projeto_integrador_iv.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import senac.ads.projeto_integrador_iv.models.Marca;
import senac.ads.projeto_integrador_iv.service.MarcaService;


@RestController
@RequestMapping("/api/v1/marca")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public ResponseEntity<List<Marca>> buscarTodos(){
        return marcaService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> buscarMarcaPorId(@PathVariable UUID id){
        return marcaService.buscarMarcaPorId(id);
    }

    @PostMapping
    public ResponseEntity<Marca> criarMarca(@RequestBody Marca marca){
        return marcaService.salvarMarca(marca);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarMarca(@PathVariable("id") UUID id, @RequestBody Marca marca){
        return marcaService.atualizarMarca(id, marca);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarMarca(@PathVariable UUID id){
        marcaService.deletarMarca(id);
        return ResponseEntity.noContent().build();
    }
}

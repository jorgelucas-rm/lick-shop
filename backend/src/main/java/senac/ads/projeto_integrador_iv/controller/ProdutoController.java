package senac.ads.projeto_integrador_iv.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.SchemaOutputResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Produto> criarProduto(
            @RequestPart("produto") String produtoJson,
            @RequestPart(value = "imagens", required = false) List<MultipartFile> imagens) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        ProdutoTO produto = objectMapper.readValue(produtoJson, ProdutoTO.class);

        return produtoService.salvarProduto(produto, imagens);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Produto> atualizarProduto(
            @PathVariable("id") UUID id,
            @RequestPart("produto") String produtoJson,
            @RequestPart(value = "imagens", required = false) List<MultipartFile> imagens) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        ProdutoAtualizadoTO produto = objectMapper.readValue(produtoJson, ProdutoAtualizadoTO.class);

        return produtoService.atualizarProduto(id, produto, imagens);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> deletarProduto(@PathVariable UUID id){
        return produtoService.deletarProduto(id);
    }
}
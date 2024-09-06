package senac.ads.projeto_integrador_iv.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import senac.ads.projeto_integrador_iv.models.Usuario;
import senac.ads.projeto_integrador_iv.service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    public ResponseEntity<List<Usuario>> buscarTodos(){
        return usuarioService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable("id") UUID id){
        return usuarioService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity criarUsuario(@RequestBody Usuario usuario){
        return usuarioService.criarUsuario(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarUsuario(@PathVariable("id") UUID id, @RequestBody Usuario usuario){
        return usuarioService.atualizarUsuario(id, usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarUsuario(@PathVariable("id") UUID id){
        return usuarioService.deletarUsuario(id);
    }
}

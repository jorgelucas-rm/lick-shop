package senac.ads.projeto_integrador_iv.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import senac.ads.projeto_integrador_iv.dto.EnderecoTO;
import senac.ads.projeto_integrador_iv.dto.RegisterTO;
import senac.ads.projeto_integrador_iv.dto.UserRequest;
import senac.ads.projeto_integrador_iv.dto.UserTO;
import senac.ads.projeto_integrador_iv.models.Usuario;
import senac.ads.projeto_integrador_iv.service.UsuarioService;


@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodos() {
        return usuarioService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable UUID id) {
        return usuarioService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody UserRequest novoUsuario) {
        UserTO userTO = novoUsuario.getUserTO();
        RegisterTO registerTO = novoUsuario.getRegisterTO();
        EnderecoTO enderecoTO = novoUsuario.getEnderecoTO();

        return usuarioService.salvarUsuario(userTO, enderecoTO, registerTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable UUID id, @RequestBody Usuario atualizacaoUsuario) {
        return usuarioService.atualizarUsuario(id, atualizacaoUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable UUID id) {
        return usuarioService.deletarUsuario(id);
    }
}

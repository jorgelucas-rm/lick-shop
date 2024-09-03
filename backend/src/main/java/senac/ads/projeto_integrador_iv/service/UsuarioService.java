package senac.ads.projeto_integrador_iv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import senac.ads.projeto_integrador_iv.models.Usuario;
import senac.ads.projeto_integrador_iv.repository.UsuarioRepository;

@RestController
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity<List<Usuario>> buscarTodos(){
        return new ResponseEntity<>(usuarioRepository.findAll(),HttpStatus.OK);
    }

    public ResponseEntity<Usuario> buscarPorId(String id){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    public ResponseEntity criarUsuario(Usuario novoUsuario){
        Usuario usuario = usuarioRepository.findById(novoUsuario.getCpf()).orElse(null);
        if(usuario == novoUsuario){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        usuarioRepository.save(novoUsuario);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity atualizarUsuario(String id, Usuario atualizacaoUsuario){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usuarioRepository.save(atualizacaoUsuario);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity deletarUsuario(String id){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usuarioRepository.delete(usuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}

package senac.ads.projeto_integrador_iv.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import senac.ads.projeto_integrador_iv.models.Usuario;
import senac.ads.projeto_integrador_iv.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity<List<Usuario>> buscarTodos(){
        return new ResponseEntity<>(usuarioRepository.findAll(),HttpStatus.OK);
    }

    public ResponseEntity<Usuario> buscarPorId(UUID id){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if(usuario == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    public ResponseEntity criarUsuario(Usuario novoUsuario){
        Usuario usuario = usuarioRepository.findByCpf(novoUsuario.getCpf())
                .orElse(null);

        if(usuario == novoUsuario){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        usuarioRepository.save(novoUsuario);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity atualizarUsuario(UUID id, Usuario atualizacaoUsuario){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usuarioRepository.save(atualizacaoUsuario);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity deletarUsuario(UUID id){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usuarioRepository.delete(usuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}

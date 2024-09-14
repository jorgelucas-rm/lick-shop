package senac.ads.projeto_integrador_iv.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import senac.ads.projeto_integrador_iv.dto.EnderecoTO;
import senac.ads.projeto_integrador_iv.dto.RegisterTO;
import senac.ads.projeto_integrador_iv.dto.UserTO;
import senac.ads.projeto_integrador_iv.models.Cargo;
import senac.ads.projeto_integrador_iv.models.Usuario;
import senac.ads.projeto_integrador_iv.repository.ContaRepository;
import senac.ads.projeto_integrador_iv.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LoginService loginService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ContaRepository contaRepository;

    public ResponseEntity<List<Usuario>> buscarTodos(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return new ResponseEntity<>(usuarios,HttpStatus.OK);
    }

    public ResponseEntity<Usuario> buscarPorId(UUID id){
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if(usuarioOpt.isPresent()){
            return new ResponseEntity<>(usuarioOpt.get(),HttpStatus.OK);
        }

        return new ResponseEntity<>(usuarioOpt.get(),HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<Usuario> salvarUsuario(UserTO userTO, EnderecoTO enderecoTO, RegisterTO registerTO) {

        if(
            usuarioRepository.findByCpf(userTO.getCpf()) != null ||
            contaRepository.findByUsuario(registerTO.getUsuario()) != null ||
            contaRepository.findByEmail(registerTO.getEmail()) != null){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        Usuario usuario = new Usuario();
        usuario.setNome(userTO.getNome());
        usuario.setCpf(userTO.getCpf());
        usuario.setNascimento(userTO.getNascimento());
        usuario.getEndereco().add(enderecoService.salvarEndereco(enderecoTO));
        usuario.setConta(loginService.register(registerTO, Cargo.USER));

        usuarioRepository.save(usuario);

        return new ResponseEntity<>(usuario,HttpStatus.OK);
    }

    // *TALVEZ* dividir em 3 atualizações (Usuario, Conta e Endereço)
    public ResponseEntity<Usuario> atualizarUsuario(UUID id, Usuario atualizacaoUsuario){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deletarUsuario(UUID id){

        if(usuarioRepository.findById(id).orElse(null) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usuarioRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

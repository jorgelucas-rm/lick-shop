package senac.ads.projeto_integrador_iv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import senac.ads.projeto_integrador_iv.infra.util.JwtUtil;
import senac.ads.projeto_integrador_iv.models.Produto;
import senac.ads.projeto_integrador_iv.models.Usuario;
import senac.ads.projeto_integrador_iv.repository.UsuarioRepository;


@Service
public class CarrinhoService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    UsuarioRepository usuarioRepository;

    public ResponseEntity<Usuario> adicionarProdutoCarrinho(String authorizationHeader, Produto produto){
        Usuario usuario = jwtUtil.extractUserFromJwtToken(authorizationHeader);
        usuario.getCarrinho().add(produto);
        usuarioRepository.save(usuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
    public ResponseEntity<Usuario> removerProdutoCarrinho(String authorizationHeader, Produto produto){
        Usuario usuario = jwtUtil.extractUserFromJwtToken(authorizationHeader);
        for (Produto produtoOnCart : usuario.getCarrinho()) {
            if (produtoOnCart.equals(produto)) {
                usuario.getCarrinho().remove(produtoOnCart);
                break;
            }
        }
        usuarioRepository.save(usuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}

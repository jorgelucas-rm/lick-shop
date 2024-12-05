package senac.ads.projeto_integrador_iv.service;

import com.mercadopago.resources.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import senac.ads.projeto_integrador_iv.infra.security.CustomUserDetails;
import senac.ads.projeto_integrador_iv.models.Produto;
import senac.ads.projeto_integrador_iv.models.Usuario;
import senac.ads.projeto_integrador_iv.repository.ProdutoRepository;
import senac.ads.projeto_integrador_iv.repository.UsuarioRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CarrinhoService {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Usuario getCurrentUser() {
        return usuarioRepository.findById(customUserDetailsService.getCurrentUserid()).orElse(null);
    }

    public ResponseEntity<List<Produto>> getCarrinho() {
        Usuario usuario = getCurrentUser();
        System.out.println(usuario);
        System.out.println(usuario.getNome());

        if (usuario == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(usuario.getCarrinho(), HttpStatus.OK);
    }

    public ResponseEntity<List<Produto>> adicionarProduto(UUID produtoId){
        Usuario usuario = getCurrentUser();
        System.out.println(usuario);
        System.out.println(usuario.getNome());
        Produto produto = produtoRepository.findById(produtoId).orElse(null);

        if (usuario == null || produto == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        usuario.getCarrinho().add(produto);
        usuarioRepository.save(usuario);

        return new ResponseEntity<>(usuario.getCarrinho(), HttpStatus.OK);
    }

    public ResponseEntity<List<Produto>> removerProduto(UUID produtoId){
        Usuario usuario = getCurrentUser();

        if (usuario == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        for (Produto produto : usuario.getCarrinho()){
            if (produto.getId().equals(produtoId)){
                usuario.getCarrinho().remove(produto);
                usuarioRepository.save(usuario);
                return new ResponseEntity<>(usuario.getCarrinho(), HttpStatus.OK);

            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

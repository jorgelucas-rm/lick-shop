package senac.ads.projeto_integrador_iv.infra.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import senac.ads.projeto_integrador_iv.models.Usuario;
import senac.ads.projeto_integrador_iv.repository.UsuarioRepository;

import java.util.UUID;

@Service
public class JwtUtil {
    @Value("${api.security.token.secret}")
    private String secret;

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario extractUserFromJwtToken(String token) {
        String tokenWithoutBearer = token.substring(7);
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(tokenWithoutBearer)
                .getBody();
        UUID uuid = UUID.fromString(claims.getSubject());
        return usuarioRepository.findByContaId(uuid).orElse(null);
    }
}

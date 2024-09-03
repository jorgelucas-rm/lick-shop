package senac.ads.projeto_integrador_iv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import senac.ads.projeto_integrador_iv.models.Usuario;

public interface UsuarioRepository<Pessoa> extends JpaRepository<Usuario, Long> {
}

package senac.ads.projeto_integrador_iv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import senac.ads.projeto_integrador_iv.models.Conta;

import java.util.UUID;

@Repository
public interface ContaRepository extends JpaRepository<Conta, UUID> {
    UserDetails findByUsuario(String login);
    Conta findByEmail (String email);
}

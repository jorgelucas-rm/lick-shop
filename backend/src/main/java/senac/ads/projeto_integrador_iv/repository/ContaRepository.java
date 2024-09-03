package senac.ads.projeto_integrador_iv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import senac.ads.projeto_integrador_iv.models.Conta;

import java.util.UUID;

public interface ContaRepository extends JpaRepository<Conta, UUID> {
}

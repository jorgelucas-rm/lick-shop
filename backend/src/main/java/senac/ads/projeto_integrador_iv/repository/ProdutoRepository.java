package senac.ads.projeto_integrador_iv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.ads.projeto_integrador_iv.models.Produto;

import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
    Produto findByNome(String nome);
}

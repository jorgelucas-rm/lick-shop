package senac.ads.projeto_integrador_iv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import senac.ads.projeto_integrador_iv.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}

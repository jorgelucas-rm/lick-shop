package senac.ads.projeto_integrador_iv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.ads.projeto_integrador_iv.models.Carrinho;
import senac.ads.projeto_integrador_iv.repository.CarrinhoRepository;

import java.util.UUID;

@Service
public class CarrinhoService {
    @Autowired
    private CarrinhoRepository carrinhoRepository;

    public Carrinho obterCarrinhoPorId(UUID carrinhoId) {
        Carrinho carrinho = carrinhoRepository.findById(carrinhoId).orElse(null);
        return carrinho;
    }
}

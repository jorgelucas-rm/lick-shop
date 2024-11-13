package senac.ads.projeto_integrador_iv.service;

import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.resources.preference.Preference;
import org.springframework.stereotype.Service;
import senac.ads.projeto_integrador_iv.models.Usuario;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagamentoService {
        public String criarLinkDePagamento(Usuario usuario) throws Exception {

        List<PreferenceItemRequest> items = usuario.getCarrinho().stream().map(produto ->
                PreferenceItemRequest.builder()
                        .title(produto.getNome())
                        .quantity(1)
                        .unitPrice(BigDecimal.valueOf((float) produto.getValorVenda()))
                        .build()
        ).collect(Collectors.toList());

        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                .success("https://www.seusite.com/success")
                .failure("https://www.seusite.com/failure")
                .pending("https://www.seusite.com/pending")
                .build();

        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items)
                .backUrls(backUrls)
                .autoReturn("approved")
                .build();

        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(preferenceRequest);

        return preference.getInitPoint();
    }
}

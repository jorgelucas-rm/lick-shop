package senac.ads.projeto_integrador_iv.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String cep;

    private String logradouro;

    private String bairro;

    private String cidade;

    private String estado;

    private String numero;

    private String complemento;

    public String getEnderecoCompleto() {
        List<String> parts = Arrays.asList(this.logradouro,
                this.numero,
                this.complemento,
                this.bairro,
                this.cidade,
                this.estado);

        return parts.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.joining(", "));
    }
}

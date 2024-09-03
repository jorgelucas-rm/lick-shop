package senac.ads.projeto_integrador_iv.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Marca {
    @Id
    private Integer codigo;

    private String nome;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;
}

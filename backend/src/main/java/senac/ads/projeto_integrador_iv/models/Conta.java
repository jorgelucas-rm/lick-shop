package senac.ads.projeto_integrador_iv.models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conta {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String usuario;

    private String senha;

    private String email;

    @OneToOne
    private Cargo cargo;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;
}

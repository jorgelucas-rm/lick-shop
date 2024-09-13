package senac.ads.projeto_integrador_iv.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public enum Situacao {
    someSituation("someSituation"),
    someSituationtoo("someSituationtoo");

    private String situacao;
}

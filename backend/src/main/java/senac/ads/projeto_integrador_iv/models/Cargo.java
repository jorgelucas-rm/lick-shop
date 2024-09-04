package senac.ads.projeto_integrador_iv.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Cargo {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String cargo;
}

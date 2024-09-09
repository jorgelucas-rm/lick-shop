package senac.ads.projeto_integrador_iv.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Cargo {
    ADMIN("admin"),
    USER("user");

    private String cargo;

}

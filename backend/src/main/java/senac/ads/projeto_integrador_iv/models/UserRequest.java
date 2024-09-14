package senac.ads.projeto_integrador_iv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequest {
    private UserTO userTO;

    private EnderecoTO enderecoTO;

    private RegisterTO registerTO;
}

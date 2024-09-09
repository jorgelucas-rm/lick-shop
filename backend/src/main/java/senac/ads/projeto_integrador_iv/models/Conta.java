package senac.ads.projeto_integrador_iv.models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conta implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String usuario;

    private String senha;

    private String email;

    private Cargo cargo;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

    @Override
    public String getUsername() {
        return this.usuario;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.cargo == Cargo.ADMIN){
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
}

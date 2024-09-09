package senac.ads.projeto_integrador_iv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import senac.ads.projeto_integrador_iv.repository.ContaRepository;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    ContaRepository contaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return contaRepository.findByUsuario(username);
    }

}

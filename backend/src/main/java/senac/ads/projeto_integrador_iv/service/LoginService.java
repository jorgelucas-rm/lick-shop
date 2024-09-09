package senac.ads.projeto_integrador_iv.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import senac.ads.projeto_integrador_iv.dto.LoginTO;
import senac.ads.projeto_integrador_iv.dto.RegisterTO;
import senac.ads.projeto_integrador_iv.models.Cargo;
import senac.ads.projeto_integrador_iv.models.Conta;
import senac.ads.projeto_integrador_iv.repository.ContaRepository;

import java.time.LocalDateTime;

@Service
public class LoginService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @Autowired
    ContaRepository contaRepository;


    public ResponseEntity login(LoginTO login) {
        var loginAuthentication = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
        var auth = authenticationManager.authenticate(loginAuthentication);

        var token = tokenService.generateToken((Conta) auth.getPrincipal());

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    public ResponseEntity register(RegisterTO newRegister, Cargo cargo) {
        if(contaRepository.findByUsuario(newRegister.getUsuario()) != null ){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Conta novaConta = new Conta();
        novaConta.setUsuario(newRegister.getUsuario());
        novaConta.setEmail(newRegister.getEmail());
        novaConta.setSenha(new BCryptPasswordEncoder().encode(newRegister.getSenha()));
        novaConta.setCargo(cargo);
        novaConta.setDataCriacao(LocalDateTime.now());
        novaConta.setDataAtualizacao(LocalDateTime.now());

        contaRepository.save(novaConta);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

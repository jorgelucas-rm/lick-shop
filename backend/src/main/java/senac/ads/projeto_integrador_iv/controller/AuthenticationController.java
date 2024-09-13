package senac.ads.projeto_integrador_iv.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import senac.ads.projeto_integrador_iv.dto.LoginTO;
import senac.ads.projeto_integrador_iv.dto.RegisterTO;
import senac.ads.projeto_integrador_iv.models.Cargo;
import senac.ads.projeto_integrador_iv.models.Conta;
import senac.ads.projeto_integrador_iv.repository.ContaRepository;
import senac.ads.projeto_integrador_iv.service.LoginService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginTO login) {
        return loginService.login(login);
    }
}
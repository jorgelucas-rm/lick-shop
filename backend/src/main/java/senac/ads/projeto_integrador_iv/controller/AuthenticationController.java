package senac.ads.projeto_integrador_iv.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.ads.projeto_integrador_iv.dto.LoginTO;
import senac.ads.projeto_integrador_iv.service.LoginService;

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
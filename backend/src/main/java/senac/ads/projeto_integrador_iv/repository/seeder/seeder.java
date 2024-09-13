package senac.ads.projeto_integrador_iv.repository.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import senac.ads.projeto_integrador_iv.dto.RegisterTO;
import senac.ads.projeto_integrador_iv.models.Cargo;
import senac.ads.projeto_integrador_iv.repository.ContaRepository;
import senac.ads.projeto_integrador_iv.service.LoginService;

@Component
public class seeder {

    @Autowired
    LoginService loginService;

    @Autowired
    ContaRepository contaRepository;

    @EventListener
    public void seeder(ContextRefreshedEvent event) {
        this.UserSeeder();
    }

    private void UserSeeder() {
        if(contaRepository.findByUsuario("root") == null){
            System.out.println("Criando usuario root");
            RegisterTO root = new RegisterTO("root", "admin", "root@root.com");
            loginService.register(root, Cargo.ADMIN);
        }
        if(contaRepository.findByUsuario("tester") == null) {
            System.out.println("Criando usuario tester");
            RegisterTO tester = new RegisterTO("tester", "tester", "teste@tester.com");
            loginService.register(tester, Cargo.USER);
        }
    }
}

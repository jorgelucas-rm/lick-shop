package senac.ads.projeto_integrador_iv;

import com.mercadopago.MercadoPagoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
		MercadoPagoConfig.setAccessToken("YOUR_ACCESS_TOKEN");
	}
}

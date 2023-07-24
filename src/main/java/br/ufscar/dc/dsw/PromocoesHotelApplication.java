package br.ufscar.dc.dsw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.dao.IHotelDAO;
import br.ufscar.dc.dsw.dao.IPromocaoDAO;
import br.ufscar.dc.dsw.dao.ISiteDAO;
import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.domain.Usuario;

@SpringBootApplication
public class PromocoesHotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(PromocoesHotelApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(IUsuarioDAO usuarioDAO, BCryptPasswordEncoder encoder, IHotelDAO hotelDAO, ISiteDAO livroDAO, IPromocaoDAO promoDAO) {
		return (args) -> {			
			Usuario u1 = new Usuario("admin","admin","admin");
			u1.setEmail("admin");
			u1.setSenha(encoder.encode("admin"));
			u1.setEnabled(true);
			usuarioDAO.save(u1);
			

		};
	}
}

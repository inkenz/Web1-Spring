package br.ufscar.dc.dsw;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.dao.IHotelDAO;
import br.ufscar.dc.dsw.dao.IPromocaoDAO;
import br.ufscar.dc.dsw.dao.ISiteDAO;
import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.domain.Hotel;
import br.ufscar.dc.dsw.domain.Site;
import br.ufscar.dc.dsw.domain.Usuario;

@SpringBootApplication
public class PromocoesHotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(PromocoesHotelApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(IUsuarioDAO usuarioDAO, BCryptPasswordEncoder encoder, IHotelDAO hotelDAO, ISiteDAO siteDAO, IPromocaoDAO promoDAO) {
		return (args) -> {		
			
			Usuario u1 = new Usuario();
			u1.setEmail("admin@admin.com");
			u1.setSenha(encoder.encode("admin"));
			u1.setEnabled(true);
			u1.setRole("ROLE_ADMIN");
			usuarioDAO.save(u1);
			
			Hotel h1 = new Hotel("hotel1@hotel.com", encoder.encode("123"), "52.183.821/0001-04", "Hotel 1", "São Paulo");
		
			
			Usuario u2 = new Usuario();
			u2.setEmail(h1.getEmail());
			u2.setSenha(h1.getSenha());
			u2.setEnabled(true);
			u2.setRole("ROLE_HOTEL");
			usuarioDAO.save(u2);
			h1.setUsuario(u2);
			hotelDAO.save(h1);
			
			Hotel h2 = new Hotel("hotel2@hotel.com", encoder.encode("123"), "86.256.076/0001-06", "Hotel 2", "São Paulo");
			
			Usuario u3 = new Usuario();
			u3.setEmail(h2.getEmail());
			u3.setSenha(h2.getSenha());
			u3.setEnabled(true);
			u3.setRole("ROLE_HOTEL");
			usuarioDAO.save(u3);
			h1.setUsuario(u3);
			hotelDAO.save(h2);
			
			Hotel h3 = new Hotel("hotel3@hotel.com", encoder.encode("123"), "57.232.865/0001-09", "Hotel 3", "São Paulo");
			
			Usuario u4 = new Usuario();
			u4.setEmail(h3.getEmail());
			u4.setSenha(h3.getSenha());
			u4.setEnabled(true);
			u4.setRole("ROLE_HOTEL");
			usuarioDAO.save(u4);
			h1.setUsuario(u4);
			hotelDAO.save(h3);
			
			Site s1 = new Site("site1@site.com", encoder.encode("123"), "site1.com.br", "Site 1", "11963169791");
			
			Usuario u5 = new Usuario();
			u5.setEmail(s1.getEmail());
			u5.setSenha(s1.getSenha());
			u5.setEnabled(true);
			u5.setRole("ROLE_SITE");
			usuarioDAO.save(u5);
			s1.setUsuario(u5);
			siteDAO.save(s1);
			
			Site s2 = new Site("site2@site.com", encoder.encode("123"), "site2.com.br", "Site 2", "11963269791");
			
			Usuario u6 = new Usuario();
			u6.setEmail(s2.getEmail());
			u6.setSenha(s2.getSenha());
			u6.setEnabled(true);
			u6.setRole("ROLE_SITE");
			usuarioDAO.save(u6);
			s1.setUsuario(u6);
			siteDAO.save(s2);
			
			Site s3 = new Site("site3@site.com", encoder.encode("123"), "site3.com.br", "Site 3", "11953269791");
			
			Usuario u7 = new Usuario();
			u7.setEmail(s3.getEmail());
			u7.setSenha(s3.getSenha());
			u7.setEnabled(true);
			u7.setRole("ROLE_SITE");
			usuarioDAO.save(u7);
			s3.setUsuario(u7);
			siteDAO.save(s3);
			
			
			List<Hotel> l1 = hotelDAO.findAll();
			for(Hotel hotel: l1) {
				System.out.print(hotel.getNome()+"\n");
			}
			
			List<Site> l2 = siteDAO.findAll();
			for(Site site: l2) {
				System.out.print(site.getNome()+"\n");
			}
			
		};
	}
}

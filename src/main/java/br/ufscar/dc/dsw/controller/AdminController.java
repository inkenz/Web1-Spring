package br.ufscar.dc.dsw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Hotel;
import br.ufscar.dc.dsw.domain.Site;
import br.ufscar.dc.dsw.service.spec.IHotelService;
import br.ufscar.dc.dsw.service.spec.ISiteService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private IHotelService hservice;
	
	@Autowired
	private ISiteService sservice;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping(value={"/index","/"})
	public String index1() {
		return "admin/index";
	}

	/*
	 * PARTE PARA O HOTEL
	 * */
	
	@GetMapping("/cadastrarHotel")
	public String cadastrar(Hotel hotel, ModelMap model) {
		Hotel h = new Hotel();
		h.setId(null);
		model.addAttribute("hotel", h);
		
		return "admin/cadastroHotel";
	}
	
	
	@PostMapping("/salvarHotel")
	public String salvarHotel(@Valid Hotel hotel, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "admin/cadastroHotel";
		}
		hotel.setSenha(encoder.encode(hotel.getSenha()));
		hservice.salvar(hotel);
		attr.addFlashAttribute("sucess", "hotel.create.sucess");
		return "redirect:/hotel/listar";
	}
	
	@GetMapping("/editarHotel/{id}")
	public String preEditarH(@PathVariable("id") String id, ModelMap model) {
		model.addAttribute("hotel", hservice.buscarPorId(Long.parseLong(id)));
		return "admin/cadastroHotel";
	}
	
	@PostMapping("/editarHotel")
	public String editarH(@Valid Hotel hotel, BindingResult result, RedirectAttributes attr) {
		
		// Apenas rejeita se o problema não for com o CNPJ (CNPJ campo read-only) 
		
		System.out.print("\n\n");
		System.out.print(result.getFieldError("CNPJ"));
		
		
		if (result.getFieldErrorCount() > 1 || result.getFieldError("CNPJ") == null) {
			System.out.print("\n\n\n\n\n\nERRO NA EDIÇÃO\n\n\n\n\n\n");
			return "admin/cadastroHotel";
		}
		hotel.setSenha(encoder.encode(hotel.getSenha()));
		hservice.salvar(hotel);
		//attr.addFlashAttribute("sucess", "editora.edit.sucess");
		return "redirect:/hotel/listar";
	}
	
	@GetMapping("/excluirHotel/{id}")
	public String excluirH(@PathVariable("id") String id, ModelMap model) {
		hservice.excluir(Long.parseLong(id));
		model.addAttribute("sucess", "editora.delete.sucess");
		
		return "redirect:/hotel/listar";
	}
	
	/*
	 * PARTE PARA O SITE
	 * */
	
	@GetMapping("/cadastrarSite")
	public String cadastrarS(Site site, ModelMap model) {
		Site s = new Site();
		s.setId(null);
		model.addAttribute("site", s);
		
		return "admin/cadastroSite";
	}
	
	
	@PostMapping("/salvarSite")
	public String salvarSite(@Valid Site site, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "admin/cadastroSite";
		}
		site.setSenha(encoder.encode(site.getSenha()));
		sservice.salvar(site);
		attr.addFlashAttribute("sucess", "site.create.sucess");
		return "redirect:/site/listar";
	}
	
	@GetMapping("/editarSite/{id}")
	public String preEditarS(@PathVariable("id") String id, ModelMap model) {
		model.addAttribute("site", sservice.buscarPorId(Long.parseLong(id)));
		return "admin/cadastroSite";
	}
	
	@PostMapping("/editarSite")
	public String editarS(@Valid Site site, BindingResult result, RedirectAttributes attr) {
		// Apenas rejeita se o problema não for com a URL (URL campo read-only) 
		System.out.print("\n\n\n Email: "+site.getEmail()+
				"\n Nome: "+site.getNome()+
				"\n Telefone "+site.getTelefone()+
				"\n URL: "+site.getURL()+"\n\n\n");
		System.out.print(result.getFieldErrorCount());
		System.out.print("\n\n");
		System.out.print(result.getFieldError("nome"));
		System.out.print("\n\n");
		System.out.print(result.getFieldError("telefone"));
		System.out.print("\n\n");
		System.out.print(result.getFieldError("URL"));
		System.out.print("\n\n");
		System.out.print(result.getFieldError("senha"));
		System.out.print("\n\n");
		System.out.print(result.getFieldError("email"));
		
		if (result.getFieldErrorCount() > 1 || result.getFieldError("URL") == null) {
			System.out.print("\n\n\n\n\n\nERRO NA EDIÇÃO\n\n\n\n\n\n");
			return "admin/cadastroSite";
		}
		site.setSenha(encoder.encode(site.getSenha()));
		sservice.salvar(site);
		attr.addFlashAttribute("sucess", "site.edit.sucess");
		return "redirect:/site/listar";
	}
	
	@GetMapping("/excluirSite/{id}")
	public String excluirS(@PathVariable("id") String id, ModelMap model) {
		sservice.excluir(Long.parseLong(id));
		model.addAttribute("sucess", "site.delete.sucess");
		
		return "redirect:/site/listar";
	}
	
}
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
import br.ufscar.dc.dsw.service.spec.IHotelService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private IHotelService hservice;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping("/index")
	public String index() {
		return "admin/index";
	}
	
	@GetMapping("/cadastrarHotel")
	public String cadastrar(Hotel hotel) {
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
		return "redirect:/listarHoteis";
	}
	
	@GetMapping("/editarHotel/{id}")
	public String preEditar(@PathVariable("id") String id, ModelMap model) {
		model.addAttribute("editora", hservice.buscarPorCNPJ(id));
		return "hotel/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Hotel hotel, BindingResult result, RedirectAttributes attr) {
		
		if (result.getFieldErrorCount() > 1 || result.getFieldError("CNPJ") == null) {
			return "hotel/cadastro";
		}

		hservice.salvar(hotel);
		//attr.addFlashAttribute("sucess", "editora.edit.sucess");
		return "redirect:/hotel/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") String id, ModelMap model) {
		hservice.excluir(id);
		model.addAttribute("sucess", "editora.delete.sucess");
		
		return "";//listar(model);
	}
}
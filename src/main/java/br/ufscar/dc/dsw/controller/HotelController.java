package br.ufscar.dc.dsw.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/hotel")
public class HotelController {
	
	@Autowired
	private IHotelService service;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Hotel hotel) {
		return "hotel/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		List<Hotel> l1 = service.buscarTodos();
		/*
		for(Hotel hotel: l1) {
			System.out.print(hotel);
		}
		*/
		model.addAttribute("hoteis",l1);
		return "hotel/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Hotel hotel, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "hotel/cadastro";
		}
		
		service.salvar(hotel);
		//attr.addFlashAttribute("sucess", "editora.create.sucess");
		return "redirect:/editoras/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") String id, ModelMap model) {
		model.addAttribute("hotel", service.buscarPorCNPJ(id));
		return "hotel/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Hotel hotel, BindingResult result, RedirectAttributes attr) {
		
		if (result.getFieldErrorCount() > 1 || result.getFieldError("CNPJ") == null) {
			return "hotel/cadastro";
		}

		service.salvar(hotel);
		//attr.addFlashAttribute("sucess", "editora.edit.sucess");
		return "redirect:/hotel/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") String id, ModelMap model) {
		service.excluir(id);
		model.addAttribute("sucess", "editora.delete.sucess");
		
		return listar(model);
	}
}
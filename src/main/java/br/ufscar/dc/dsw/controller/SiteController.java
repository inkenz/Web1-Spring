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

import br.ufscar.dc.dsw.domain.Site;
import br.ufscar.dc.dsw.service.spec.ISiteService;

@Controller
@RequestMapping("/site")
public class SiteController {
	
	@Autowired
	private ISiteService service;
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		List<Site> l1 = service.buscarTodos();
		/*
		for(Hotel hotel: l1) {
			System.out.print(hotel);
		}
		*/
		model.addAttribute("sites",l1);
		return "site/lista";
	}
}
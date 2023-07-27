package br.ufscar.dc.dsw.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Hotel;
import br.ufscar.dc.dsw.domain.Promocao;
import br.ufscar.dc.dsw.domain.Site;
import br.ufscar.dc.dsw.service.spec.IHotelService;
import br.ufscar.dc.dsw.service.spec.IPromocaoService;
import br.ufscar.dc.dsw.service.spec.ISiteService;

@Controller
@RequestMapping("/hotel")
public class HotelController {
	
	@Autowired
	private IHotelService hservice;
	@Autowired
	private ISiteService sservice;
	@Autowired
	private IPromocaoService pservice;
	
	@GetMapping(value={"/index","/"})
	public String index1() {
		return "hotel/index";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		List<Hotel> l1 = hservice.buscarTodos();
		
		model.addAttribute("hoteis",l1);
		return "hotel/lista";
	}
	
	@GetMapping("/cadastrarPromocao")
	public String cadastrarPromocao(Promocao promocao, ModelMap model) {
		List<Site> sites = sservice.buscarTodos();
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		String email = a.getName();
		
		System.out.print("\n\n\n\n"+email+"\n\n\n\n");
		
		String cnpj = hservice.buscarPorEmail(email).getCNPJ();
		System.out.print("\n\n\n\n O CNPJ É: "+cnpj+"\n\n\n\n");
		
		model.addAttribute("sites",sites);
		model.addAttribute("hotelCNPJ", cnpj);
		model.addAttribute("promocao", promocao);
		return "hotel/cadastroPromocao";
	}
	
	@PostMapping("/salvarPromocao")
	public String salvarSite(@Valid Promocao promocao, BindingResult result, RedirectAttributes attr, ModelMap model) {
		if (result.hasErrors()) {
			return cadastrarPromocao(promocao, model);
		}
		
		pservice.salvar(promocao);
		attr.addFlashAttribute("sucess", "site.create.sucess");
		return "redirect:/hotel/index";
	}
}
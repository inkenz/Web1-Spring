package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.ISiteDAO;
import br.ufscar.dc.dsw.dao.IPromocaoDAO;
import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.domain.Site;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.spec.ISiteService;


@Service
@Transactional(readOnly = false)
public class SiteService implements ISiteService {

	@Autowired
	ISiteDAO sdao;
	@Autowired
	IPromocaoDAO pdao;
	@Autowired
	IUsuarioDAO udao;


	@Transactional(readOnly = true)
	public Site buscarPorEndereco(String endereco) {
		return sdao.findByURL(endereco);
	}

	@Transactional(readOnly = true)
	public List<Site> buscarTodos() {
		return sdao.findAll();
	}


	
	public void salvar(Site site) {
		Usuario u = new Usuario();
		u.setEmail(site.getEmail());
		u.setSenha(site.getSenha());
		u.setEnabled(true);
		u.setRole("ROLE_SITE");
		
		udao.save(u);
		sdao.save(site);
	}

	
	public void excluir(String endereco) {
		udao.deleteByEmail(this.buscarPorEndereco(endereco).getEmail());
		if(this.siteTemPromocoes(endereco)) {
			while(this.siteTemPromocoes(endereco)) {
				pdao.deleteById(pdao.findByEndereco(endereco).getId());
			}
		}	
		sdao.deleteByURL(endereco);
	}

	@Transactional(readOnly = true)
	public boolean siteTemPromocoes(String endereco) {
		return pdao.findByEndereco(endereco) != null;
	}
		

}
package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Site;

public interface ISiteService {

	Site buscarPorEndereco(String endereco);

	List<Site> buscarTodos();

	void salvar(Site site);

	void excluir(String endereco);
	
	boolean siteTemPromocoes(String endereco);
}
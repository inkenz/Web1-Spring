package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Hotel;
import br.ufscar.dc.dsw.domain.Promocao;
import br.ufscar.dc.dsw.domain.Site;

public interface IPromocaoService {

	Promocao buscarPorId(long id);

	List<Promocao> buscarTodosPorHotel(Hotel hotel);
	List<Promocao> buscarTodosPorSite(Site site);
	
	void salvar(Promocao promocao);
	void excluir(Long id);
}
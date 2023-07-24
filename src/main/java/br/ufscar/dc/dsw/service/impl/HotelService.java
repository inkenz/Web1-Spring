package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IHotelDAO;
import br.ufscar.dc.dsw.dao.IPromocaoDAO;
import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.domain.Hotel;
import br.ufscar.dc.dsw.domain.Promocao;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.spec.IHotelService;

@Service
@Transactional(readOnly = false)
public class HotelService implements IHotelService {

	@Autowired
	IHotelDAO hdao;
	@Autowired
	IPromocaoDAO pdao;
	@Autowired
	IUsuarioDAO udao;
	
	@Transactional(readOnly = true)
	public Hotel buscarPorCNPJ(String cnpj) {
		return hdao.findByCNPJ(cnpj);
	}

	@Transactional(readOnly = true)
	public List<Hotel> buscarTodos() {
		return hdao.findAll();
	}
	
	public List<Hotel> buscarTodosPorCidade(String cidade){
		List<Hotel> lh = null;
		
		for(Hotel h: hdao.findAll()) {
			if(h.getCidade() == cidade) lh.add(h);
		}
		
		return lh;
	}
	public void salvar(Hotel hotel) {
		Usuario u = new Usuario(hotel.getEmail(),hotel.getSenha(), "hotel");
		udao.save(u);
		hdao.save(hotel);
	}

	public void excluir(String cnpj) {
		udao.deleteByEmail(this.buscarPorCNPJ(cnpj).getEmail());
		if(this.hotelTemPromocoes(cnpj)) {
			while(this.hotelTemPromocoes(cnpj)) {
				pdao.deleteById(pdao.findByCNPJ(cnpj).getId());
			}
		}	
		hdao.deleteByCNPJ(cnpj);
	}

	//@Transactional(readOnly = true);
	public boolean hotelTemPromocoes(String cnpj) {
		return pdao.findByCNPJ(cnpj) != null;
	}
	

}
package br.ufscar.dc.dsw.service.impl;

import java.util.ArrayList;
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
		List<Hotel> lh = new ArrayList<Hotel>();
		
		for(Hotel h: hdao.findAll()) {
			if(h.getCidade() == cidade) lh.add(h);
		}
		
		return lh;
	}
	public void salvar(Hotel hotel) {
		Usuario u = new Usuario();
		u.setEmail(hotel.getEmail());
		u.setSenha(hotel.getSenha());
		u.setEnabled(true);
		u.setRole("ROLE_HOTEL");
		
		udao.save(u);
		hdao.save(hotel);
	}

	public void excluir(long id) {
		udao.deleteByEmail(this.buscarPorId(id).getEmail());
		if(this.hotelTemPromocoes(buscarPorId(id).getCNPJ())) {
			while(this.hotelTemPromocoes(buscarPorId(id).getCNPJ())) {
				pdao.deleteById(pdao.findByCNPJ(buscarPorId(id).getCNPJ()).getId());
			}
		}	
		hdao.deleteById(id);
	}

	//@Transactional(readOnly = true);
	public boolean hotelTemPromocoes(String cnpj) {
		return pdao.findByCNPJ(cnpj) != null;
	}
	
	public Hotel buscarPorEmail(String email) {
		List<Hotel> hoteis = this.buscarTodos();
		Hotel hotel = null;
		for(Hotel h: hoteis) {
			if(h.getEmail().equals(email)) hotel = h;
		}
		
		return hotel;
	}


	@Override
	public Hotel buscarPorId(long id) {
		return hdao.findById(id);
	}
	

}
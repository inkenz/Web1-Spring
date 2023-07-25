package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Hotel;

public interface IHotelService {
	Hotel buscarPorId(long id);
	
	Hotel buscarPorCNPJ(String cnpj);

	List<Hotel> buscarTodos();
	
	List<Hotel> buscarTodosPorCidade(String cidade);

	void salvar(Hotel hotel);

	void excluir(String cnpj);
	
	boolean hotelTemPromocoes(String cnpj);
}
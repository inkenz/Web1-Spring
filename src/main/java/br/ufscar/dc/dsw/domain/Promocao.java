package br.ufscar.dc.dsw.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="Promocao")
public class Promocao  extends AbstractEntity<Long>{
	/*
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	*/
	@Size(min = 3)
	@NotEmpty
	@Column(nullable = false, unique = false, length = 70)
	private String endereco;
	
	@Size(min = 18)
	@NotEmpty
	@Column(nullable = false, unique = false, length = 14)
	private String CNPJ;
	
	@Min(1)
	@NotEmpty
	@Column(nullable = false, unique = false)
	private float preco;
	
	@NotEmpty
	@FutureOrPresent
	@Column(nullable = false, unique = false)
	private Date inicio;
	
	@NotEmpty
	@FutureOrPresent
	@Column(nullable = false, unique = false)
	private Date fim;
	
	@ManyToOne
	private Hotel hotel;
	
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public Hotel getHotel() {
		return hotel;
	}
	
	@ManyToOne 
	private Site site;
	
	public void setSite(Site site) {
		this.site = site;
	}
	
	public Site getSite() {
		return site;
	}
	public Promocao() {}
	public Promocao(String endereco, String CNPJ, float preco, Date inicio, Date fim){
		this.endereco = endereco;
		this.CNPJ = CNPJ;
		this.preco = preco;
		this.inicio = inicio;
		this.fim = fim;
	}
	/*
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	*/
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getCNPJ() {
		return CNPJ;
	}
	
	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}
	
	public float getPreco() {
		return preco;
	}
	
	public void setPreco(float preco) {
		this.preco = preco;
	}
	
	public Date getInicio() {
		return inicio;
	}
	
	@SuppressWarnings("deprecation")
	public void setInicio(int ano, int mes, int dia) {
		Date aux = new Date(ano, mes, dia);
		this.inicio = aux;
	}
	
	public Date getFim() {
		return fim;
	}
	
	@SuppressWarnings("deprecation")
	public void setFim(int ano, int mes, int dia) {
		Date aux = new Date(ano, mes, dia);
		this.fim = aux;
	}
	
	// PARA DEBUG
    @Override
	public String toString() {
		return "Promocao = [CNPJ = " + CNPJ + ", endereco=" + endereco +"]";
	}
} 
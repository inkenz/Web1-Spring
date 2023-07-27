package br.ufscar.dc.dsw.domain;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Promocao")
public class Promocao  extends AbstractEntity<Long>{
	@Size(min = 3)
	@NotEmpty
	@Column(nullable = false, unique = false, length = 70)
	private String URL;
	
	@Size(min = 18)
	@NotEmpty
	@Column(nullable = false, unique = false, length = 14)
	private String CNPJ;
	
	@Min(1)
	@NotNull
	@Column(nullable = false, unique = false)
	private float preco;
	
	@NotNull
	@FutureOrPresent
	@Column(nullable = false, unique = false)
	private Date inicio;
	
	@NotNull
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
	public Promocao(String URL, String CNPJ, float preco, Date inicio, Date fim){
		this.URL = URL;
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
	public String getURL() {
		return URL;
	}
	
	public void setURL(String URL) {
		this.URL = URL;
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
} 
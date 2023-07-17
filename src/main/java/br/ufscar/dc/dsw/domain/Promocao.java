package br.ufscar.dc.dsw.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Promocao")
public class Promocao{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, unique = false, length = 70)
	private String endereco;
	@Column(nullable = false, unique = false, length = 14)
	private String CNPJ;
	@Column(nullable = false, unique = false)
	private float preco;
	@Column(nullable = false, unique = false)
	private Date inicio;
	@Column(nullable = false, unique = false)
	private Date fim;
	
	public Promocao(String endereco, String CNPJ, float preco, Date inicio, Date fim){
		this.endereco = endereco;
		this.CNPJ = CNPJ;
		this.preco = preco;
		this.inicio = inicio;
		this.fim = fim;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
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
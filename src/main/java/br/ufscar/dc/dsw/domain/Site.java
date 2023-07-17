package br.ufscar.dc.dsw.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@Table(name = "Site")
public class Site{
	
	@Column(nullable = false, unique = true, length = 50)
	private String email;

	@Column(nullable = false, unique = false, length = 100)
	private String senha;
	
	@Id
	private String URL;
	
	@Column(nullable = false, unique = false, length = 70)
	private String nome;
	
	@Column(nullable = false, unique = false, length = 15)
	private String telefone;
	
	@OneToMany(targetEntity = Promocao.class, mappedBy = "Hotel", fetch = FetchType.EAGER)
	private Set<Promocao> promocoes;
	
	@OneToOne
	private Usuario usuario;
	
	public Site(String email, String senha, String endereco, String nome, String telefone){
		this.email = email;
		this.senha = senha;
		this.URL = endereco;
		this.nome = nome;
		this.telefone = telefone;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getURL() {
		return URL;
	}
	
	public void setURL(String URL) {
		URL = URL;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Usuario getUsuario() {
    	return this.usuario;
    }
    public void setUsuario(Usuario user) {
    	this.usuario = user;
    }
    public Set<Promocao> getPromocoes(){
    	return this.promocoes;
    }
    public void setPromocoes(Set<Promocao> promocoes) {
    	this.promocoes = promocoes;
    }
    
    // PARA DEBUG
    @Override
	public String toString() {
		return "Site = [Nome = " + nome + ", email=" + email +", senha="+senha+ "]";
	}
}
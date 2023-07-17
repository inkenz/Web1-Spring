package br.ufscar.dc.dsw.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@Table(name="Hotel")
public class Hotel {
	@Id
    private String CNPJ;
	
	@Column(nullable = false, unique = true, length = 50)
    private String email;
	
	@Column(nullable = false, unique = false, length = 100)
    private String senha;
	
	@Column(nullable = false, unique = false, length = 50)
    private String nome;
	
	@Column(nullable = false, unique = false, length = 50)
    private String cidade;
    
	@OneToMany(targetEntity = Promocao.class, mappedBy = "Hotel", fetch = FetchType.EAGER)
	private Set<Promocao> promocoes;
	
	@OneToOne
	private Usuario usuario;
	
    public Hotel(String email, String senha, String CNPJ, String nome, String cidade, Usuario usuario){
        this.email = email;
        this.senha = senha;
        this.CNPJ = CNPJ;
        this.nome = nome;
        this.cidade = cidade;
        this.usuario = usuario;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getSenha(){
        return senha;
    }
    public void setSenha(String senha){
        this.senha = senha;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
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
		return "Hotel = [Nome = " + nome + ", email=" + email +", senha="+senha+ "]";
	}
    
}
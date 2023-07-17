package br.ufscar.dc.dsw.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import jakarta.persistence.OneToOne;


@Entity
@Table(name = "Usuario")
public class Usuario {
	@Id
	private String email;
	@Column(nullable = false, unique = true, length = 100)
	private String senha;
	@Column(nullable = false, unique = true, length = 5)
	private String papel;

	@OneToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private Hotel hotel;
	
	@OneToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private Site site;
	
	
	public Usuario(String email, String senha, String papel) {
		this.email = email;
		this.senha = senha;
		this.papel = papel;
	}


	public String getEmail() {
		return this.email;
	}

	public void setLogin(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String password) {
		this.senha = password;
	}

	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}
	
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	public Hotel getHotel() {
		return this.hotel;
	}
	
	public void setSite(Site site) {
		this.site = site;
	}
	
	public Site getSite() {
		return this.site;
	}
	
}

package com.samuex.financeiro.controller;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private Long unidadeNegocio;
	private String LocalUsuario;
	private Date dataLogin;
	private String Perfil;
	
	public boolean isLogado() {
		return nome != null;
	}
	
		
	public Boolean perfilContabilidade(){
		return this.getPerfil() == "UNIDADE";
	}

	public Boolean perfilUnidade(){
		return this.getPerfil() == "CONTABILIDADE";  
	}

	public Boolean perfilAdministrador(){
		return this.getPerfil() == "UNIDADE"; 
	}

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataLogin() {
		return dataLogin;
	}

	public void setDataLogin(Date dataLogin) {
		this.dataLogin = dataLogin;
	}

	public Long getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(Long unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getLocalUsuario() {
		return LocalUsuario;
	}

	public void setLocalUsuario(String localUsuario) {
		LocalUsuario = localUsuario;
	}

	public String getPerfil() {
		return Perfil;
	}

	public void setPerfil(String perfil) {
		Perfil = perfil;
	}


}
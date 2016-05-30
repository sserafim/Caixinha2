package com.samuex.financeiro.controller;


import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.samuex.financeiro.repository.UsuarioSistemas;

@Named
@RequestScoped
public class LoginBean {

	@Inject
	private Usuario usuario;
	
	@Inject
	private UsuarioSistemas usuarios;
		
	private String nomeUsuario;
	private String senha;

	
	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		if (this.usuarios.buscaLogin(this.nomeUsuario).equals(this.nomeUsuario) && "123".equals(this.senha)) {
			this.usuario.setNome(this.nomeUsuario);
			this.usuario.setDataLogin(new Date());
		//	this.usuario.setLocalUsuario(this.usuarios.porLogin(this.nomeUsuario).getEmpresaUnidade().getNomeUnidade().toString());

			
			
			return "/ConsultaLancamentos?faces-redirect=true";
		} else {
			FacesMessage mensagem = new FacesMessage("Usuário/senha inválidos!");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
		
		return null;
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/Login?faces-redirect=true";
	}
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


}
package com.samuex.financeiro.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.samuex.financeiro.model.UsuarioSistema;
import com.samuex.financeiro.repository.UsuarioSistemas;

@Named
@javax.faces.view.ViewScoped
public class CadastroUsuarioSistemaBean implements Serializable {
	
	public static final long serialVersionUID = 1L;

	private UsuarioSistema usuarioSistema;
	
	public void prepararCadastro(){
		
		if(this.usuarioSistema == null){
			this.usuarioSistema = new UsuarioSistema();
		}
	}
	
	@Inject
	private UsuarioSistemas usuarioSistemas;
	
	
	
	public void salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		this.usuarioSistemas.guardar(this.usuarioSistema);		
		context.addMessage(null, new FacesMessage("Usuário salvo com sucesso!!!"));
	}
	

	public UsuarioSistema getUsuarioSistema() {
		return usuarioSistema;
	}

	public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
		this.usuarioSistema = usuarioSistema;
	}
	

}

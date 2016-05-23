package com.samuex.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.samuex.financeiro.model.Empresa;
import com.samuex.financeiro.model.TipoPerfil;
import com.samuex.financeiro.model.UsuarioSistema;
import com.samuex.financeiro.repository.Empresas;
import com.samuex.financeiro.repository.UsuarioSistemas;

@Named
@javax.faces.view.ViewScoped
public class CadastroUsuarioSistemaBean implements Serializable {
	
	public static final long serialVersionUID = 1L;

	private UsuarioSistema usuarioSistema;

	@Inject
	private UsuarioSistemas usuarioSistemas;
	
	@Inject
	private Empresas listEmpresas;
	
	
	public void prepararCadastro(){
		
		if(this.usuarioSistema == null){
			this.usuarioSistema = new UsuarioSistema();
		}
	}
	
	
	public void empresaSelecionada(AjaxBehaviorEvent event){
	
		
	}
	
	
	public void salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		this.usuarioSistemas.guardar(this.usuarioSistema);		
		context.addMessage(null, new FacesMessage("Usuário salvo com sucesso!!!"));
	}
	
	public List<UsuarioSistema> listUsuariosSistema(){
		return usuarioSistemas.todos();
	}
	
	
	public List<Empresa> listaEmpresas(){
		return this.listEmpresas.todas();
	}
	
	public TipoPerfil[] getTipoPerfil(){
		return TipoPerfil.values();
	}
	

	public UsuarioSistema getUsuarioSistema() {
		return usuarioSistema;
	}

	public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
		this.usuarioSistema = usuarioSistema;
	}
	

}

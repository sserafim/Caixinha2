package com.samuex.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.samuex.financeiro.model.Empresa;
import com.samuex.financeiro.model.TipoPerfil;
import com.samuex.financeiro.model.UsuarioSistema;
import com.samuex.financeiro.repository.Empresas;
import com.samuex.financeiro.repository.UsuarioSistemas;
import com.samuex.financeiro.service.CadastroUsuarioSistema;
import com.samuex.financeiro.service.NegocioException;

@Named
@javax.faces.view.ViewScoped
public class CadastroUsuarioSistemaBean implements Serializable {
	
	public static final long serialVersionUID = 1L;

	private UsuarioSistema usuarioSistema;

	@Inject
	private UsuarioSistemas usuarioSistemas;
	
	@Inject
	private CadastroUsuarioSistema cadastro;
	
	@Inject
	private Empresas empresas;
	
	private List<Empresa> todasEmpresas;

	public void prepararCadastro(){
		this.todasEmpresas = this.empresas.todas();
		
		if(this.usuarioSistema == null){
			this.usuarioSistema = new UsuarioSistema();
		}
	}
	
	
	public void salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		try{
			this.cadastro.salvar(this.usuarioSistema);	
			this.usuarioSistema = new UsuarioSistema();
			context.addMessage(null, new FacesMessage("Usuário salvo com sucesso!!!"));
		
		}catch(NegocioException e) {
			
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
		
	}
	
	public List<UsuarioSistema> listUsuariosSistema(){
		return usuarioSistemas.todos();
	}
	
	
	public List<Empresa> getTodasEmpresas() {
		return todasEmpresas;
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

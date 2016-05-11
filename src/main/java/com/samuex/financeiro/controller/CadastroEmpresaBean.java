package com.samuex.financeiro.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.samuex.financeiro.model.Empresa;
import com.samuex.financeiro.service.CadastroEmpresas;
import com.samuex.financeiro.service.NegocioException;


@Named
@javax.faces.view.ViewScoped
public class CadastroEmpresaBean implements Serializable{
	
	public static final long serialVersionUID = 1L;
	
	private Empresa empresa;
	
	@Inject
	private CadastroEmpresas cadastro;
	
	public void prepararCadastro(){
		
		if (this.empresa == null){
			this.empresa = new Empresa();
		}
	}
	
	public void salvar() throws NegocioException{
		FacesContext context = FacesContext.getCurrentInstance();
		
		this.cadastro.salvar(this.empresa);
			
		context.addMessage(null, new FacesMessage("Empresa atualizada com sucesso!!"));
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
		
}

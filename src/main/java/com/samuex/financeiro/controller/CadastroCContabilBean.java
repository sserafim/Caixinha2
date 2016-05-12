package com.samuex.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.samuex.financeiro.model.ContaContabil;
import com.samuex.financeiro.repository.ContasContabeis;
import com.samuex.financeiro.service.CadastroCContabil;
import com.samuex.financeiro.service.NegocioException;

@Named
@javax.faces.view.ViewScoped
public class CadastroCContabilBean implements Serializable{
	
	public static final long serialVersionUID = 1L;
	
	///// Variáveis -----------------------------------	
	
	private ContaContabil contaContabil;
	
	@Inject
	private CadastroCContabil cadastro;
	
	@Inject
	private ContasContabeis contasContabeis;
	

	/////  ------------------------------------------------	
	
	
	public void prepararCadastro(){
		
		if (this.contaContabil == null){
			this.contaContabil = new ContaContabil();
		}
	}
	
	public void salvar() throws NegocioException{
		FacesContext context = FacesContext.getCurrentInstance();
		
		this.cadastro.salvar(this.contaContabil);
		context.addMessage(null, new FacesMessage("Conta Contábil atualizado com sucesso!!"));		
	}
	
	public List<ContaContabil> listContasContabeis(){
		return contasContabeis.todas();
	}

	public ContaContabil getContaContabil() {
		return contaContabil;
	}

	public void setContaContabil(ContaContabil contaContabil) {
		this.contaContabil = contaContabil;
	}
	
}

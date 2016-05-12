package com.samuex.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.samuex.financeiro.model.ContaContabil;
import com.samuex.financeiro.repository.ContasContabeis;
import com.samuex.financeiro.service.CadastroCContabil;
import com.samuex.financeiro.service.NegocioException;

public class ConsultaCContabilBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ContasContabeis lancamentosRepository;
	
	@Inject
	private CadastroCContabil cadastro;
	
	private List<ContaContabil> contasContabeis;
	
	private ContaContabil contaContabilSelecionado;
	
	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			this.cadastro.excluir(this.contaContabilSelecionado);
			this.consultar();
			
			context.addMessage(null, new FacesMessage("Conta Contabil excluído com sucesso!"));
		} catch (NegocioException e) {
			
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}
	
	public void consultar(){
		this.contasContabeis = lancamentosRepository.todas();
	}	
	
	public List<ContaContabil> getContasContabeis(){
		return contasContabeis;
	}

	public ContaContabil getContaContabilSelecionado() {
		return contaContabilSelecionado;
	}

	public void setContaContabilSelecionado(ContaContabil contaContabilSelecionado) {
		this.contaContabilSelecionado = contaContabilSelecionado;
	}
	
	
	
	
	
}

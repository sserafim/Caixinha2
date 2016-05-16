package com.samuex.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.samuex.financeiro.model.ContaContabil;
import com.samuex.financeiro.repository.ContasContabeis;
import com.samuex.financeiro.service.CadastroCContabil;
import com.samuex.financeiro.service.NegocioException;

@Named
@ViewScoped
public class ConsultaCContabilBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ContasContabeis lancamentosRepository;
	
	@Inject
	private CadastroCContabil cadastro;
	
	private List<ContaContabil> contasContabeis;
	
	private ContaContabil contaContabil;
	
	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			this.cadastro.excluir(this.contaContabil);
			this.consultar();
			
			context.addMessage(null, new FacesMessage("Conta Contabil excluído com sucesso!"));
		} catch (NegocioException e) {
			
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}
		
	public void delRecords(){		
		FacesContext context = FacesContext.getCurrentInstance();
		
		this.cadastro.excluirTodas();
		this.consultar();

		context.addMessage(null, new FacesMessage("Todos os registro foram deletados!"));
	}
		
	
	public void consultar(){
		this.contasContabeis = lancamentosRepository.todas();
	}	
	
	public List<ContaContabil> getContasContabeis(){
		return contasContabeis;
	}

	public ContaContabil getContaContabil() {
		return contaContabil;
	}

	public void setContaContabil(ContaContabil contaContabil) {
		this.contaContabil = contaContabil;
	}
	
}

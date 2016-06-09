package com.samuex.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.samuex.financeiro.model.ContaContabil;
import com.samuex.financeiro.model.HistoricoPadrao;
import com.samuex.financeiro.model.TipoLote;
import com.samuex.financeiro.repository.ContasContabeis;
import com.samuex.financeiro.repository.HistoricosPadrao;
import com.samuex.financeiro.service.CadastroHistPadrao;
import com.samuex.financeiro.service.NegocioException;

@Named
@javax.faces.view.ViewScoped
public class CadastroHistPadraoBean implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	private HistoricoPadrao historicoPadrao;
	
	private List<ContaContabil> todasCtaCtb;
	
	@Inject
	private ContasContabeis contasContabeis;
	
	@Inject
	private HistoricosPadrao historicosPadrao;
	
	@Inject
	private CadastroHistPadrao cadastro;
	
	
	public void prepararCadastro(){
		
		this.todasCtaCtb = this.contasContabeis.todas();
		
		if(this.historicoPadrao == null){
			this.historicoPadrao = new HistoricoPadrao();
		}
	}
	
	
	public void salvar() throws NegocioException{
		FacesContext context = FacesContext.getCurrentInstance();
		
		this.cadastro.salvar(historicoPadrao);
		this.historicoPadrao = new HistoricoPadrao();
		context.addMessage(null, new FacesMessage("Histórico atualizado com sucesso"));
	}

	
	public TipoLote[] getTipoLote(){
		return TipoLote.values();
	} 
	
	
	public List<HistoricoPadrao> listHistoricoPadrao(){
		return historicosPadrao.todos();
	}
	

	public HistoricoPadrao getHistoricoPadrao() {
		return historicoPadrao;
	}


	public void setHistoricoPadrao(HistoricoPadrao historicoPadrao) {
		this.historicoPadrao = historicoPadrao;
	}


	public List<ContaContabil> getTodasCtaCtb() {
		return todasCtaCtb;
	}


}

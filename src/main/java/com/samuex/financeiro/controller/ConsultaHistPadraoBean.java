package com.samuex.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.samuex.financeiro.model.HistoricoPadrao;
import com.samuex.financeiro.repository.HistoricosPadrao;
import com.samuex.financeiro.service.CadastroHistPadrao;

@Named
@ViewScoped
public class ConsultaHistPadraoBean implements Serializable {

	public static final long serialVersionUID = 1L;
	
	@Inject
	private HistoricosPadrao historicosRepository;
	
	@Inject
	private CadastroHistPadrao cadastro;
	
	private List<HistoricoPadrao> historicos;
	
	private HistoricoPadrao historicoPadrao;
	
	public void excluir(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		this.cadastro.excluir(this.historicoPadrao);
		
		context.addMessage(null, new FacesMessage("Histórico excluído com sucesso"));		
	}
	
	public void delRecords(){		
		FacesContext context = FacesContext.getCurrentInstance();
		
		this.cadastro.excluirTodos();

		context.addMessage(null, new FacesMessage("Todos os registro foram deletados!"));
	}
	
	public void consultar(){
		this.historicos = historicosRepository.todos();
	}

	public HistoricoPadrao getHistoricoPadrao() {
		return historicoPadrao;
	}

	public void setHistoricoPadrao(HistoricoPadrao historicoPadrao) {
		this.historicoPadrao = historicoPadrao;
	}

	public List<HistoricoPadrao> getHistoricos() {
		return historicos;
	}

}

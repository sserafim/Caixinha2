package com.samuex.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.samuex.financeiro.model.CentroCusto;
import com.samuex.financeiro.repository.CentroCustos;
import com.samuex.financeiro.service.CadastroCCustos;
import com.samuex.financeiro.service.NegocioException;


@Named
@ViewScoped
public class ConsultaCCustoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CentroCustos lancamentosRepository;
	
	@Inject
	private CadastroCCustos cadastro; 
	
	private List<CentroCusto> centroCustos;
	
	private CentroCusto centroCustoSelecionado;
	
	
	
	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			this.cadastro.excluir(this.centroCustoSelecionado);
			this.consultar();
			
			context.addMessage(null, new FacesMessage("Centro de Custo excluído com sucesso!"));
		} catch (NegocioException e) {
			
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}
	
	public void consultar(){
		this.centroCustos = lancamentosRepository.todas();
	}

	public List<CentroCusto> getCentroCustos() {
		return centroCustos;
	}

	public CentroCusto getCentroCustoSelecionado() {
		return centroCustoSelecionado;
	}

	public void setCentroCustoSelecionado(CentroCusto centroCustoSelecionado) {
		this.centroCustoSelecionado = centroCustoSelecionado;
	}

}

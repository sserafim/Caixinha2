package com.samuex.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.samuex.financeiro.model.UnidadeNegocio;
import com.samuex.financeiro.repository.UnidadesNegocio;
import com.samuex.financeiro.service.CadastroUnidadeNegocio;
import com.samuex.financeiro.service.NegocioException;

@Named
@javax.faces.view.ViewScoped
public class ConsultaUnidadeNegocioBean implements Serializable{
	
	public static final long serialVersionUID = 1L;
	
	@Inject
	private UnidadesNegocio unidadeNegRepository;
	
	@Inject
	private CadastroUnidadeNegocio cadastro;
	
	private UnidadeNegocio unidadeNegSelecionada;
	
	private List<UnidadeNegocio> listUnidadeNeg;
	
	
	public void consultar(){
		this.listUnidadeNeg = unidadeNegRepository.todas();
	}
	
	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			this.cadastro.excluir(this.unidadeNegSelecionada);
			this.consultar();
			
			context.addMessage(null, new FacesMessage("Unidade de Negócio excluída com sucesso!"));
		} catch (NegocioException e) {
			
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public UnidadeNegocio getUnidadeNegSelecionada() {
		return unidadeNegSelecionada;
	}

	public void setUnidadeNegSelecionada(UnidadeNegocio unidadeNegSelecionada) {
		this.unidadeNegSelecionada = unidadeNegSelecionada;
	}

	public List<UnidadeNegocio> getListUnidadeNeg() {
		return listUnidadeNeg;
	}

	public void setListUnidadeNeg(List<UnidadeNegocio> listUnidadeNeg) {
		this.listUnidadeNeg = listUnidadeNeg;
	}
	
	
	
	
	

}

package com.samuex.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.samuex.financeiro.model.CentroCusto;
import com.samuex.financeiro.model.Empresa;
import com.samuex.financeiro.repository.CentroCustos;
import com.samuex.financeiro.repository.Empresas;
import com.samuex.financeiro.service.CadastroCCustos;
import com.samuex.financeiro.service.NegocioException;

@Named
@javax.faces.view.ViewScoped
public class CadastroCCustoBean implements Serializable{
	
	public static final long serialVersionUID = 1L;
	
	///// Variáveis -----------------------------------	
	
	private CentroCusto centroCusto;
	
	@Inject
	private CadastroCCustos cadastro;
	
	@Inject
	private Empresas empresas;
	
	@Inject
	private CentroCustos centroCustos;
	
	private List<Empresa> todasEmpresas;
	
	/////  ------------------------------------------------
	
	
	public void prepararCadastro(){
		this.todasEmpresas = this.empresas.todas();
		
		if (this.centroCusto == null){
			this.centroCusto = new CentroCusto();
		}
	}
	
	public List<String> pesquisarDescricoesEmp(String descricao){
		return this.centroCustos.descricaoQueContem(descricao);
	}
	
	public void salvar() throws NegocioException{
		FacesContext context = FacesContext.getCurrentInstance();
		
		this.cadastro.salvar(this.centroCusto);
			
		context.addMessage(null, new FacesMessage("Centro de Csutos atualizado com sucesso!!"));
	}
	
	public List<CentroCusto> listCentroCusto(){
		return centroCustos.todas();
	}		

	public List<Empresa> getTodasEmpresas() {
		return todasEmpresas;
	}

	public CentroCusto getCentroCusto() {
		return centroCusto;
	}

	public void setCentroCusto(CentroCusto centroCusto) {
		this.centroCusto = centroCusto;
	}

	
	
}

package com.samuex.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.samuex.financeiro.model.Empresa;
import com.samuex.financeiro.repository.Empresas;
import com.samuex.financeiro.service.CadastroEmpresas;
import com.samuex.financeiro.service.NegocioException;


@Named
@javax.faces.view.ViewScoped
public class ConsultaEmpresaBean implements Serializable{
	
	public static final long serialVersionUID = 1L;
	
	@Inject
	private Empresas empresaRepository;
	
	@Inject
	private CadastroEmpresas cadastro;
	
	private Empresa empresaSelecionada;
	
	private List<Empresa> empresas;
	
	public void consultar(){
		this.empresas = empresaRepository.todas(); 
	}
	
	
	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			this.cadastro.excluir(this.empresaSelecionada);
			this.consultar();
			
			context.addMessage(null, new FacesMessage("Lançamento excluído com sucesso!"));
		} catch (NegocioException e) {
			
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}


	public Empresa getEmpresaSelecionada() {
		return empresaSelecionada;
	}


	public void setEmpresaSelecionada(Empresa empresaSelecionada) {
		this.empresaSelecionada = empresaSelecionada;
	}
	
	

}

package com.samuex.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.samuex.financeiro.model.LancamentoSaque;
import com.samuex.financeiro.repository.LancamentosSaques;
import com.samuex.financeiro.service.CadastroLacamentoSaque;
import com.samuex.financeiro.service.NegocioException;


@Named
@ViewScoped
public class ConsultaLancamentoSaqueBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject 
	private LancamentosSaques lancamentosRepository;

	@Inject
	private CadastroLacamentoSaque cadastro;

	private List<LancamentoSaque> lancamentosSaques;
	
	private LancamentoSaque lancamentoSelecionado;
	
	
	public void excluir(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		try{
			this.cadastro.excluir(this.lancamentoSelecionado);
		
			context.addMessage( null, new FacesMessage("Lancamento Excluído com sucesso!!"));			
		}catch (NegocioException e){
			
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);						
		}
	}
		
	public void consultar(){
		this.lancamentosSaques = lancamentosRepository.todos();
	}
	
	public List<LancamentoSaque> getLancamentosSaques() {
		return lancamentosSaques;
	}

	public LancamentoSaque getLancamentoSelecionado() {
		return lancamentoSelecionado;
	}

	public void setLancamentoSelecionado(LancamentoSaque lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}

		
}





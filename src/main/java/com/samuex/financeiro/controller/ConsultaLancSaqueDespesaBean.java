package com.samuex.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.samuex.financeiro.model.LancamentoSaqueDespesa;
import com.samuex.financeiro.model.UnidadeNegocio;
import com.samuex.financeiro.repository.LancamentosSaquesDespesas;
import com.samuex.financeiro.repository.UnidadesNegocio;
import com.samuex.financeiro.service.CadastroLacamentoSaqueDespesa;
import com.samuex.financeiro.service.CadastroUnidadeNegocio;
import com.samuex.financeiro.service.NegocioException;


@Named
@ViewScoped
public class ConsultaLancSaqueDespesaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuario usuarioLogado;
	

	@Inject 
	private LancamentosSaquesDespesas lancamentosRepository;

	@Inject
	private CadastroLacamentoSaqueDespesa cadastro;
	
	@Inject
	private UnidadesNegocio unidadesNegocioDAO;
	
	@Inject
	private CadastroUnidadeNegocio unidadeNegocioService;

	private List<LancamentoSaqueDespesa> lancamentosSaques;
	
	private LancamentoSaqueDespesa lancamentoSelecionado;
	
	
	public void excluirSaque(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		try{
			UnidadeNegocio un = unidadesNegocioDAO.porId(this.usuarioLogado.getUnidadeNegocio());
			un.setSaldoAtual(un.getSaldoAtual().subtract(this.lancamentoSelecionado.getValorLancamento()));
			
			this.unidadeNegocioService.salvar(un);
			
			this.cadastro.excluir(this.lancamentoSelecionado);
		
			context.addMessage( null, new FacesMessage("Lancamento Excluído com sucesso!!"));			
		}catch (NegocioException e){
			
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);						
		}
	}

	public void excluirDespesa(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		try{		
			UnidadeNegocio un = unidadesNegocioDAO.porId(this.usuarioLogado.getUnidadeNegocio());
			un.setSaldoAtual(un.getSaldoAtual().add(this.lancamentoSelecionado.getValorLancamento()));
			
			this.unidadeNegocioService.salvar(un);
			
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
	
	public List<LancamentoSaqueDespesa> getLancamentosSaques() {
		return lancamentosSaques;
	}

	public LancamentoSaqueDespesa getLancamentoSelecionado() {
		return lancamentoSelecionado;
	}

	public void setLancamentoSelecionado(LancamentoSaqueDespesa lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}

		
}





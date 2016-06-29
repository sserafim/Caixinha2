package com.samuex.financeiro.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.samuex.financeiro.model.LancamentoSaqueDespesa;
import com.samuex.financeiro.model.TipoLancamento;
import com.samuex.financeiro.model.TipoSaque;
import com.samuex.financeiro.model.UnidadeNegocio;
import com.samuex.financeiro.repository.CentroCustos;
import com.samuex.financeiro.repository.HistoricosPadrao;
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
	private CentroCustos centroCustoDAO;
	
	@Inject
	private HistoricosPadrao historicoPadraoDAO;	

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
			
			
			if(verificaSaldoCaixinha(un.getSaldoAtual()) != null){
				FacesMessage mensagem = new FacesMessage("ERRO: Saldo não pode ficar negativo!!");
				mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
				context.addMessage(null, mensagem);					
			}else{
				un.setSaldoAtual(un.getSaldoAtual().subtract(this.lancamentoSelecionado.getValorLancamento()));
				this.unidadeNegocioService.salvar(un);			
				this.cadastro.excluir(this.lancamentoSelecionado);
				context.addMessage( null, new FacesMessage("Lancamento Excluído com sucesso!!"));		
			}		
		}catch (NegocioException e){
			
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
								
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
	
	public void fechamentoCaixinha(Object[] lancamentosFecham) throws ParseException, NegocioException{
		
		Object[] valores = this.lancamentosRepository.buscaTotaisFechamentoCx((Date) lancamentosFecham[0]);
		
		Date data = (Date) valores[0];
		String local = (String) valores[1];
		BigDecimal saque = (BigDecimal) valores[2];
		BigDecimal despesa = (BigDecimal) valores[3];
		
		if(saque.doubleValue() > 0){
		  LancamentoSaqueDespesa lancamentoSaque = new LancamentoSaqueDespesa();
			lancamentoSaque.setDataLancamento(data);
			lancamentoSaque.setLocal(local);
			lancamentoSaque.setTipoLancamento(TipoLancamento.SAQUE);
			lancamentoSaque.setValorLancamento(saque);
			lancamentoSaque.setTipoSaque(TipoSaque.FECHAMENTO);
			lancamentoSaque.setCentroCusto(this.centroCustoDAO.buscaCCustoGeral());
			lancamentoSaque.setHistoricoPadrao(this.historicoPadraoDAO.porId("9001"));
			lancamentoSaque.setUsuarioLancamento("ADMIN");
			
			this.cadastro.salvar(lancamentoSaque);
		}
			
		if(despesa.doubleValue() > 0){
		  LancamentoSaqueDespesa lancamentoDespesa = new LancamentoSaqueDespesa();
			lancamentoDespesa.setDataLancamento(data);
			lancamentoDespesa.setLocal(local);
			lancamentoDespesa.setTipoLancamento(TipoLancamento.DESPESA);
			lancamentoDespesa.setValorLancamento(despesa);
			lancamentoDespesa.setTipoSaque(TipoSaque.FECHAMENTO);
			lancamentoDespesa.setCentroCusto(this.centroCustoDAO.buscaCCustoGeral());
			lancamentoDespesa.setHistoricoPadrao(this.historicoPadraoDAO.porId("9000"));
			lancamentoDespesa.setUsuarioLancamento("ADMIN");
						
		    this.cadastro.salvar(lancamentoDespesa);
		}
		

		FacesContext context = FacesContext.getCurrentInstance();		
		this.lancamentosRepository.geraArquivoFechamentoCaixinha((Date) lancamentosFecham[0]);
		context.addMessage(null, new FacesMessage("Fechamento caixinha efetuado com sucesso!!!"));
	}
	
	public String verificaSaldoCaixinha(BigDecimal valor){
			if(valor.doubleValue() < this.lancamentoSelecionado.getValorLancamento().doubleValue()){
				return "Erro";
			}else{			
				return null;
			}		
	}

	public void consultar(){
		this.lancamentosSaques = lancamentosRepository.todos();
	}
	
	public List<LancamentoSaqueDespesa> getLancamentosSaques() {
		return lancamentosSaques;
	}
	
	public List<Object[]> listLancamentosHome() {
		return lancamentosRepository.buscaLancamentosHome();
	}
	
	public List<Object[]> listLancamentosHomeDetalhe(Object[] data){
		return lancamentosRepository.buscaLancamentosHomeDetalhe((Date) data[0]);
	}
		
	public LancamentoSaqueDespesa getLancamentoSelecionado() {
		return lancamentoSelecionado;
	}

	public void setLancamentoSelecionado(LancamentoSaqueDespesa lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}

		
}





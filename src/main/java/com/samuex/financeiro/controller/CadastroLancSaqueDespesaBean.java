package com.samuex.financeiro.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
@javax.faces.view.ViewScoped
public class CadastroLancSaqueDespesaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuario usuarioLogado;

	private BigDecimal valorAtual;

	private LancamentoSaqueDespesa lancamentoSaqueDespesa;

	@Inject
	private CentroCustos centroCustosDAO;

	@Inject
	private HistoricosPadrao historicoPadraoDAO;

	@Inject
	private UnidadesNegocio unidadesNegocioDAO;

	@Inject
	private CadastroUnidadeNegocio unidadeNegocioService;

	@Inject
	private CadastroLacamentoSaqueDespesa cadastro;

	@Inject
	private LancamentosSaquesDespesas lancamentoSaquesDespesas;

	public void prepararCadastroSaque() throws ParseException {

		if (this.lancamentoSaqueDespesa == null) {
			this.lancamentoSaqueDespesa = new LancamentoSaqueDespesa();
			
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Date data = formato.parse(this.getDateTime());
			this.lancamentoSaqueDespesa.setDataLancamento(data);
			
		}
	}
	
	
	public void prepararCadastroDespesa(){

		if (this.lancamentoSaqueDespesa == null) {
			this.lancamentoSaqueDespesa = new LancamentoSaqueDespesa();
			
		}
	}	

	public void salvarSaque() {
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			this.lancamentoSaqueDespesa.setUsuarioLancamento(this.usuarioLogado.getNome());
			this.lancamentoSaqueDespesa.setTipoLancamento(TipoLancamento.SAQUE);
			
			atualizaSaldoAtual();
			atualizaCentroCusto();
			
			this.cadastro.salvar(this.lancamentoSaqueDespesa);
			consultarNovo();

			context.addMessage(null, new FacesMessage("Lançamento Saque salvo com sucesso!"));
		} catch (NegocioException | ParseException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	
	public void salvarDespesa() {
		FacesContext context = FacesContext.getCurrentInstance();

		try {	
			
			this.lancamentoSaqueDespesa.setUsuarioLancamento(this.usuarioLogado.getNome());
			this.lancamentoSaqueDespesa.setTipoLancamento(TipoLancamento.DESPESA);
			atualizaSaldoAtualDespesa();
			this.cadastro.salvar(this.lancamentoSaqueDespesa);
			this.lancamentoSaqueDespesa = new LancamentoSaqueDespesa();
		

			context.addMessage(null, new FacesMessage("Lançamento Despesa salvo com sucesso!"));
		} catch (NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}	

	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return dateFormat.format(date.getTime());
	}

	public void getAtualizaHistoricoPadrao() {
		if (this.lancamentoSaqueDespesa.getTipoSaque().getDescricao() == "BANCO") {
			this.lancamentoSaqueDespesa.setHistoricoPadrao(this.historicoPadraoDAO.porId("9001"));

		} else if (this.lancamentoSaqueDespesa.getTipoSaque().getDescricao() == "TESOURARIA") {
			this.lancamentoSaqueDespesa.setHistoricoPadrao(this.historicoPadraoDAO.porId("9002"));
		}
	}

	public void atualizaCentroCusto() {			
		this.lancamentoSaqueDespesa.setCentroCusto(this.centroCustosDAO.porId(centroCustosDAO.buscaCCusto()));
	}

	public void atualizaSaldoAtual() {

		UnidadeNegocio un = unidadesNegocioDAO.porId(this.usuarioLogado.getUnidadeNegocio());
		this.lancamentoSaqueDespesa
				.setLocal(un.getEmpresa().getRazaoSocial().concat(" - ").concat(un.getNomeUnidade()));
		if (this.lancamentoSaqueDespesa.getId() == null) {
			un.setSaldoAtual(un.getSaldoAtual().add(this.lancamentoSaqueDespesa.getValorLancamento()));
			this.unidadeNegocioService.salvar(un);
		} else {
			valorAtual = this.lancamentoSaquesDespesas.porId(this.lancamentoSaqueDespesa.getId()).getValorLancamento();
			un.setSaldoAtual(un.getSaldoAtual().subtract(valorAtual).add(this.lancamentoSaqueDespesa.getValorLancamento()));
			this.unidadeNegocioService.salvar(un);
		}
	}
	
	
	public void atualizaSaldoAtualDespesa() {

		UnidadeNegocio un = unidadesNegocioDAO.porId(this.usuarioLogado.getUnidadeNegocio());
		this.lancamentoSaqueDespesa
				.setLocal(un.getEmpresa().getRazaoSocial().concat(" - ").concat(un.getNomeUnidade()));
		if (this.lancamentoSaqueDespesa.getId() == null){
			un.setSaldoAtual(un.getSaldoAtual().subtract(this.lancamentoSaqueDespesa.getValorLancamento()));
			this.unidadeNegocioService.salvar(un);
		}else{
			valorAtual = this.lancamentoSaquesDespesas.porId(this.lancamentoSaqueDespesa.getId()).getValorLancamento();
			un.setSaldoAtual(un.getSaldoAtual().add(valorAtual).subtract(this.lancamentoSaqueDespesa.getValorLancamento()));
			this.unidadeNegocioService.salvar(un);
		}
	}	
	

	public void consultarNovo() throws ParseException {
		this.lancamentoSaqueDespesa = new LancamentoSaqueDespesa();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data = formato.parse(this.getDateTime());
		this.lancamentoSaqueDespesa.setDataLancamento(data);
	}

	public TipoSaque[] getTiposSaques() {
		return TipoSaque.values();
	}
	
	
	public List<LancamentoSaqueDespesa> listSaquesDespesas() {
		return lancamentoSaquesDespesas.todos();
	}

	public List<LancamentoSaqueDespesa> listSaquesUnidades() {
		return lancamentoSaquesDespesas.buscaPorUnidadeSaque();
	}
	
	public List<LancamentoSaqueDespesa> listDespesasUnidades() {
		return lancamentoSaquesDespesas.buscaPorUnidadeDespesa();
	} 

	public LancamentoSaqueDespesa getLancamentoSaqueDespesa() {
		return lancamentoSaqueDespesa;
	}

	public void setLancamentoSaqueDespesa(LancamentoSaqueDespesa lancamentoSaqueDespesa) {
		this.lancamentoSaqueDespesa = lancamentoSaqueDespesa;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

}

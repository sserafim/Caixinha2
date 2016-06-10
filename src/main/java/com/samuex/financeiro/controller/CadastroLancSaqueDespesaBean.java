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
import com.samuex.financeiro.model.TipoSaque;
import com.samuex.financeiro.model.UnidadeNegocio;
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
	private HistoricosPadrao historicoPadraoDAO;

	@Inject
	private UnidadesNegocio unidadesNegocioDAO;

	@Inject
	private CadastroUnidadeNegocio unidadeNegocioService;

	@Inject
	private CadastroLacamentoSaqueDespesa cadastro;

	@Inject
	private LancamentosSaquesDespesas lancamentoSaquesDespesas;

	public void prepararCadastro() throws ParseException {

		if (this.lancamentoSaqueDespesa == null) {
			this.lancamentoSaqueDespesa = new LancamentoSaqueDespesa();

			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Date data = formato.parse(this.getDateTime());
			this.lancamentoSaqueDespesa.setDataSaque(data);
		}
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			this.lancamentoSaqueDespesa.setUsuarioSaque(this.usuarioLogado.getNome());
			atualizaSaldoAtual();
			this.cadastro.salvar(this.lancamentoSaqueDespesa);
			consultarNovo();

			context.addMessage(null, new FacesMessage("Lançamento salvo com sucesso!"));
		} catch (NegocioException | ParseException e) {
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

	public void atualizaSaldoAtual() {

		UnidadeNegocio un = unidadesNegocioDAO.porId(this.usuarioLogado.getUnidadeNegocio());
		this.lancamentoSaqueDespesa.setLocalSaque(un.getEmpresa().getRazaoSocial().concat(" - ").concat(un.getNomeUnidade()));

		if (this.lancamentoSaqueDespesa.getId() == null) {
			un.setSaldoAtual(un.getSaldoAtual().add(this.lancamentoSaqueDespesa.getValorSaque()));
			this.unidadeNegocioService.salvar(un);
		} else {
			valorAtual = this.lancamentoSaquesDespesas.porId(this.lancamentoSaqueDespesa.getId()).getValorSaque();
			un.setSaldoAtual(un.getSaldoAtual().subtract(valorAtual).add(this.lancamentoSaqueDespesa.getValorSaque()));
			this.unidadeNegocioService.salvar(un);
		}
	}

	public void consultarNovo() throws ParseException {
		this.lancamentoSaqueDespesa = new LancamentoSaqueDespesa();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data = formato.parse(this.getDateTime());
		this.lancamentoSaqueDespesa.setDataSaque(data);
	}

	public TipoSaque[] getTiposSaques() {
		return TipoSaque.values();
	}

	public List<LancamentoSaqueDespesa> listSaquesDespesas() {
		return lancamentoSaquesDespesas.todos();
	}

	public List<LancamentoSaqueDespesa> listSaquesUnidades() {
		return lancamentoSaquesDespesas.buscaPorUnidade();
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

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

import com.samuex.financeiro.model.LancamentoSaque;
import com.samuex.financeiro.model.TipoSaque;
import com.samuex.financeiro.model.UnidadeNegocio;
import com.samuex.financeiro.repository.HistoricosPadrao;
import com.samuex.financeiro.repository.LancamentosSaques;
import com.samuex.financeiro.repository.UnidadesNegocio;
import com.samuex.financeiro.service.CadastroLacamentoSaque;
import com.samuex.financeiro.service.CadastroUnidadeNegocio;
import com.samuex.financeiro.service.NegocioException;

@Named
@javax.faces.view.ViewScoped
public class CadastroLancamentoSaqueBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuario usuarioLogado;

	private BigDecimal valorAtual;

	private LancamentoSaque lancamentoSaque;

	@Inject
	private HistoricosPadrao historicoPadraoDAO;

	@Inject
	private UnidadesNegocio unidadesNegocioDAO;

	@Inject
	private CadastroUnidadeNegocio unidadeNegocioService;

	@Inject
	private CadastroLacamentoSaque cadastro;

	@Inject
	private LancamentosSaques lancamentoSaques;

	public void prepararCadastro() throws ParseException {

		if (this.lancamentoSaque == null) {
			this.lancamentoSaque = new LancamentoSaque();

			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Date data = formato.parse(this.getDateTime());
			this.lancamentoSaque.setDataSaque(data);
		}
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			this.lancamentoSaque.setUsuarioSaque(this.usuarioLogado.getNome());
			atualizaSaldoAtual();
			this.cadastro.salvar(this.lancamentoSaque);
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
		if (this.lancamentoSaque.getTipoSaque().getDescricao() == "BANCO") {
			this.lancamentoSaque.setHistoricoPadrao(this.historicoPadraoDAO.porId("9001"));

		} else if (this.lancamentoSaque.getTipoSaque().getDescricao() == "TESOURARIA") {
			this.lancamentoSaque.setHistoricoPadrao(this.historicoPadraoDAO.porId("9002"));
		}
	}

	public void atualizaSaldoAtual() {

		UnidadeNegocio un = unidadesNegocioDAO.porId(this.usuarioLogado.getUnidadeNegocio());
		this.lancamentoSaque.setLocalSaque(un.getEmpresa().getRazaoSocial().concat(" - ").concat(un.getNomeUnidade()));

		if (this.lancamentoSaque.getId() == null) {
			un.setSaldoAtual(un.getSaldoAtual().add(this.lancamentoSaque.getValorSaque()));
			this.unidadeNegocioService.salvar(un);
		} else {
			valorAtual = this.lancamentoSaques.porId(this.lancamentoSaque.getId()).getValorSaque();
			un.setSaldoAtual(un.getSaldoAtual().subtract(valorAtual).add(this.lancamentoSaque.getValorSaque()));
			this.unidadeNegocioService.salvar(un);
		}
	}

	public void consultarNovo() throws ParseException {
		this.lancamentoSaque = new LancamentoSaque();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data = formato.parse(this.getDateTime());
		this.lancamentoSaque.setDataSaque(data);
	}

	public TipoSaque[] getTiposSaques() {
		return TipoSaque.values();
	}

	public List<LancamentoSaque> listSaques() {
		return lancamentoSaques.todos();
	}

	public List<LancamentoSaque> listSaquesUnidades() {
		return lancamentoSaques.buscaPorUnidade();
	}

	public LancamentoSaque getLancamentoSaque() {
		return lancamentoSaque;
	}

	public void setLancamentoSaque(LancamentoSaque lancamentoSaque) {
		this.lancamentoSaque = lancamentoSaque;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

}

package com.samuex.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.samuex.financeiro.model.LancamentoSaque;
import com.samuex.financeiro.model.TipoSaque;
import com.samuex.financeiro.model.UnidadeNegocio;
import com.samuex.financeiro.repository.LancamentosSaques;
import com.samuex.financeiro.repository.UnidadesNegocio;
import com.samuex.financeiro.service.CadastroLacamentoSaque;
import com.samuex.financeiro.service.NegocioException;


@Named
@javax.faces.view.ViewScoped
public class CadastroLancamentoSaqueBean  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuario usuarioLogado;
	
	private LancamentoSaque lancamentoSaque;
	
	@Inject
	private UnidadesNegocio unidadesNegocioDAO;
	
	@Inject
	private CadastroLacamentoSaque cadastro;
	
	@Inject
	private LancamentosSaques lancamentoSaques;

	public void prepararCadastro(){
					
		if(this.lancamentoSaque == null){
			this.lancamentoSaque = new LancamentoSaque();
		}
	}
	
	public void salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		try{
			UnidadeNegocio un = unidadesNegocioDAO.porId(this.usuarioLogado.getUnidadeNegocio());		
			this.lancamentoSaque.setUsuarioSaque(this.usuarioLogado.getNome());
			this.lancamentoSaque.setLocalSaque(un.getEmpresa().getRazaoSocial().concat(" - ").concat(un.getNomeUnidade()));
			un.setSaldoAtual(un.getSaldoAtual().add(this.lancamentoSaque.getValorSaque()));
					
			
			
			this.cadastro.salvar(this.lancamentoSaque);	
			unidadesNegocioDAO.atualizaSaldo(un);	

			
			this.lancamentoSaque = new LancamentoSaque();
			context.addMessage(null, new FacesMessage("Lançamento salvo com sucesso!"));
		
		}catch(NegocioException e) {
			
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);			
		}
	}
	
	public TipoSaque[] getTiposSaques(){
		return TipoSaque.values();
	}
	
	public List<LancamentoSaque> listSaques(){
		return lancamentoSaques.todos();		
	}
	
	public List<LancamentoSaque> listSaquesUnidades(){
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

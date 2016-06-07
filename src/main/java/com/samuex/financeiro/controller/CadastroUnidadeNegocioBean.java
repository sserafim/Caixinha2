package com.samuex.financeiro.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.samuex.financeiro.model.UnidadeNegocio;
import com.samuex.financeiro.repository.UnidadesNegocio;
import com.samuex.financeiro.service.CadastroUnidadeNegocio;
import com.samuex.financeiro.service.NegocioException;


@Named
@javax.faces.view.ViewScoped
public class CadastroUnidadeNegocioBean implements Serializable{
	
	public static final long serialVersionUID = 1L;
	
	private UnidadeNegocio unidadeNegocio;
	private Double valor = 0.00;
	
	@Inject
	private Usuario usuario;
	
	@Inject
	private UnidadesNegocio unidadesNegocio;
	
	@Inject
	private CadastroUnidadeNegocio cadastro;
	
	public void prepararCadastro(){
		
		if (this.unidadeNegocio == null){
			this.unidadeNegocio = new UnidadeNegocio();
			this.unidadeNegocio.setSaldoInicial(new BigDecimal(valor));
			this.unidadeNegocio.setSaldoAtual(new BigDecimal(valor));
		}
	}	
	
	public void salvar() throws NegocioException{
		FacesContext context = FacesContext.getCurrentInstance();
		
		unidadeNegocio.setUsuarioManutencao(this.usuario.getNome());
		this.cadastro.salvar(this.unidadeNegocio);
		
		this.unidadeNegocio = new UnidadeNegocio();
			
		context.addMessage(null, new FacesMessage("Unidade de Negócio atualizada com sucesso!!"));
	}
	
	public List<UnidadeNegocio> listUnidadeNeg(){
		return this.unidadesNegocio.todas();
	}
	
	public void getAtualizaSaldoAtual(){
		this.unidadeNegocio.setSaldoAtual(this.unidadeNegocio.getSaldoInicial().add(this.unidadeNegocio.getSaldoAtual()));
	}
	
	public void atualizaSaldoAt(AjaxBehaviorEvent e){
		this.unidadeNegocio.setSaldoAtual(this.unidadeNegocio.getSaldoInicial().add(this.unidadeNegocio.getSaldoAtual()));
	}


	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	
}

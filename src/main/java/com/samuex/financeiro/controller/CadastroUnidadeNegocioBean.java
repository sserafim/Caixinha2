package com.samuex.financeiro.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
	private BigDecimal valorAtual;
	
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
		this.unidadeNegocio.setSaldoInicial(new BigDecimal(valor));
		this.unidadeNegocio.setSaldoAtual(new BigDecimal(valor));
			
		context.addMessage(null, new FacesMessage("Unidade de Negócio atualizada com sucesso!!"));
	}
	
	public List<UnidadeNegocio> listUnidadeNeg(){
		return this.unidadesNegocio.todas();
	}
	
	public void getAtualizaSaldoAtual(){
		if(this.unidadeNegocio.getCodigo() == null){
			this.unidadeNegocio.setSaldoAtual(this.unidadeNegocio.getSaldoInicial());
		}else{
			valorAtual = this.unidadesNegocio.porId(this.unidadeNegocio.getCodigo()).getSaldoAtual();
			this.unidadeNegocio.setSaldoAtual(valorAtual.add(this.unidadeNegocio.getSaldoInicial()));
		}	
	}
		

	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	
}

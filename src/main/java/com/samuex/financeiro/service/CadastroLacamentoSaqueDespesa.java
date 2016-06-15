package com.samuex.financeiro.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.samuex.financeiro.model.LancamentoSaqueDespesa;
import com.samuex.financeiro.repository.LancamentosSaquesDespesas;
import com.samuex.financeiro.util.Transactional;

public class CadastroLacamentoSaqueDespesa implements Serializable {
	
	public static final long serialVersionUID = 1L;

	
	@Inject
	private LancamentosSaquesDespesas lancamentosSaques;
	
	
	@Transactional
	public void salvar(LancamentoSaqueDespesa lancamentoSaqueDespesa) throws NegocioException{
		
		this.lancamentosSaques.guardar(lancamentoSaqueDespesa);		
	}

	@Transactional
	public void salvarDespesa(LancamentoSaqueDespesa lancamentoSaqueDespesa) throws NegocioException{
		
		this.lancamentosSaques.adicionar(lancamentoSaqueDespesa);		
	}
	
	@Transactional
	public void excluir(LancamentoSaqueDespesa lancamentoSaqueDespesa) throws NegocioException{
		
		lancamentoSaqueDespesa = this.lancamentosSaques.porId(lancamentoSaqueDespesa.getId());
		
		this.lancamentosSaques.remover(lancamentoSaqueDespesa);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

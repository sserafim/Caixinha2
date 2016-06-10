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
	public void salvar(LancamentoSaqueDespesa lancamentoSaque) throws NegocioException{
		
		this.lancamentosSaques.guardar(lancamentoSaque);		
	}
	
	@Transactional
	public void excluir(LancamentoSaqueDespesa lancamentoSaque) throws NegocioException{
		
		lancamentoSaque = this.lancamentosSaques.porId(lancamentoSaque.getId());
		
		this.lancamentosSaques.remover(lancamentoSaque);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

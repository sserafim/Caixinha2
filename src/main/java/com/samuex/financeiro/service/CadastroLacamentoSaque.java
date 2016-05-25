package com.samuex.financeiro.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.samuex.financeiro.model.LancamentoSaque;
import com.samuex.financeiro.repository.LancamentosSaques;
import com.samuex.financeiro.util.Transactional;

public class CadastroLacamentoSaque implements Serializable {
	
	public static final long serialVersionUID = 1L;

	
	@Inject
	private LancamentosSaques lancamentosSaques;
	
	
	@Transactional
	public void salvar(LancamentoSaque lancamentoSaque) throws NegocioException{
		
		this.lancamentosSaques.guardar(lancamentoSaque);		
	}
	
	
	public void excluir(LancamentoSaque lancamentoSaque) throws NegocioException{
		
		lancamentoSaque = this.lancamentosSaques.porId(lancamentoSaque.getId());
		
		this.lancamentosSaques.remover(lancamentoSaque);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

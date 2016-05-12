package com.samuex.financeiro.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.samuex.financeiro.model.ContaContabil;
import com.samuex.financeiro.repository.ContasContabeis;
import com.samuex.financeiro.util.Transactional;

public class CadastroCContabil implements Serializable{
	
	public static final long serialVersionUID = 1L;	
	
	@Inject
	private ContasContabeis contasContabeis;
	
	@Transactional	
	public void salvar (ContaContabil contaContabil){
		this.contasContabeis.guardar(contaContabil);
	}
		
	@Transactional	
	public void excluir (ContaContabil contaContabil) throws NegocioException{
		contaContabil = this.contasContabeis.porId(contaContabil.getId());
		
		this.contasContabeis.remover(contaContabil);
	}

}

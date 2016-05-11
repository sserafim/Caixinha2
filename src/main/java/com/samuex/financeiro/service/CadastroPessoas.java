package com.samuex.financeiro.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.samuex.financeiro.model.Pessoa;
import com.samuex.financeiro.repository.Pessoas;
import com.samuex.financeiro.util.Transactional;

public class CadastroPessoas implements Serializable{
	
	private static final long serialVersionUID=1L;
	
	@Inject
	private Pessoas pessoas;
	
	@Transactional	
	public void salvar(Pessoa pessoa) {
	   this.pessoas.guardar(pessoa);		
	}

	@Transactional	
	public void excluir (Pessoa pessoa){
		this.pessoas.remover(pessoa);
	}
	

}

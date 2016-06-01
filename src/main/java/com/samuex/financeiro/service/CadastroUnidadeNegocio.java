package com.samuex.financeiro.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.samuex.financeiro.model.UnidadeNegocio;
import com.samuex.financeiro.repository.UnidadesNegocio;
import com.samuex.financeiro.util.Transactional;

public class CadastroUnidadeNegocio implements Serializable{
	
	public static final long serialVersionUID = 1L;
	
	@Inject
	private UnidadesNegocio unidadesNegocio;
	
	@Transactional
	public void salvar (UnidadeNegocio unidadeNegocio){
		this.unidadesNegocio.guardar(unidadeNegocio);
	}

	@Transactional
	public void excluir (UnidadeNegocio unidadeNegocio) throws NegocioException{
		unidadeNegocio = this.unidadesNegocio.porId(unidadeNegocio.getCodigo());
		
		this.unidadesNegocio.remover(unidadeNegocio);
	}
	
	
}

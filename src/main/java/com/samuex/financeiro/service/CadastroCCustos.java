package com.samuex.financeiro.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.samuex.financeiro.model.CentroCusto;
import com.samuex.financeiro.repository.CentroCustos;
import com.samuex.financeiro.util.Transactional;



public class CadastroCCustos implements Serializable{
	
	public static final long serialVersionUID = 1L;
	
	@Inject
	private CentroCustos centroCustos;
	
	@Transactional	
	public void salvar (CentroCusto centroCusto){
		this.centroCustos.guardar(centroCusto);
	}
		
	@Transactional	
	public void excluir (CentroCusto centroCusto) throws NegocioException{
		centroCusto = this.centroCustos.porId(centroCusto.getCodigo());
		
		this.centroCustos.remover(centroCusto);
	}

}

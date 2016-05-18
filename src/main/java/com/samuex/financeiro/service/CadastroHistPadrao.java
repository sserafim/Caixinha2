package com.samuex.financeiro.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.samuex.financeiro.model.HistoricoPadrao;
import com.samuex.financeiro.repository.HistoricosPadrao;
import com.samuex.financeiro.util.Transactional;

public class CadastroHistPadrao implements Serializable{
	
	public static final long serialVersionUID = 1L;
	
	@Inject
	private HistoricosPadrao historicosPadrao;
	
	@Transactional
	public void salvar(HistoricoPadrao historicoPadrao){
		this.historicosPadrao.guardar(historicoPadrao);
	}
	
	@Transactional
	public void excluir(HistoricoPadrao historicoPadrao){
		historicoPadrao = this.historicosPadrao.porId(historicoPadrao.getCodHistorico());
		
		this.historicosPadrao.remover(historicoPadrao);
	}
	
	@Transactional
	public void excluirTodos(){
		this.historicosPadrao.deletarTodos();
	}
}

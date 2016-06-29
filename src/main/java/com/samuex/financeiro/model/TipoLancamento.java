package com.samuex.financeiro.model;

public enum TipoLancamento {

	SAQUE("SAQUE"), 
	DESPESA("DESPESA"),
	FECHAMENTO("FECHAMENTO");

	private String descricao;
	
	TipoLancamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
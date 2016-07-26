package com.samuex.financeiro.model;

public enum TipoSaque {
	
	
	BANCO("BANCO"), 
	TESOURARIA("TESOURARIA");

	
	private String descricao;
	
	TipoSaque(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}	

}

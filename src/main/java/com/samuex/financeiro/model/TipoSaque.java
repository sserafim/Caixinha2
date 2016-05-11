package com.samuex.financeiro.model;

public enum TipoSaque {
	
	
	BANCO("Banco"), 
	TESOURARIA("Tesouraria");
	
	private String descricao;
	
	TipoSaque(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}	

}

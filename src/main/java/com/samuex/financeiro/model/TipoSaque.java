package com.samuex.financeiro.model;

public enum TipoSaque {
	
	
	BANCO("BANCO"), 
	TESOURARIA("TESOURARIA"),
	FECHAMENTO("FECHAMENTO");
	
	private String descricao;
	
	TipoSaque(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}	

}

package com.samuex.financeiro.model;

public enum TipoPerfil {
	
	ADMINISTRADOR("ADMINISTRADOR"),
	CONTABILIDADE("CONTABILIDADE"),
	UNIDADE("UNIDADE");
	
	private String descricao;
	
	private TipoPerfil(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}

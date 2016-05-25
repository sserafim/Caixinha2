package com.samuex.financeiro.model;

public enum TipoPerfil {
	
	ADMINISTRADOR("Administrador"),
	CONTABILIDADE("Contabilidade"),
	UNIDADE("Unidade");
	
	private String descricao;
	
	private TipoPerfil(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}

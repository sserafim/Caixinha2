package com.samuex.financeiro.model;

public enum TipoLote {
	
	DOIS("2");
	
	private String id;
	
	TipoLote(String id){
		this.id = id;
	}
	
	public String getId(){
		return id;
	}
	

}

package com.samuex.financeiro.model;

public enum TipoLote {
	
	TipoDoLote(2);
	
	private int id;
	
	TipoLote(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	

}

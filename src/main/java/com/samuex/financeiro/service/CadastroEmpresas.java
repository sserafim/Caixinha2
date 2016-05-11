package com.samuex.financeiro.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.samuex.financeiro.model.Empresa;
import com.samuex.financeiro.repository.Empresas;
import com.samuex.financeiro.util.Transactional;

public class CadastroEmpresas implements Serializable{
	
	public static final long serialVersionUID = 1L;
	
	@Inject
	private Empresas empresas;
	
	@Transactional	
	public void salvar (Empresa empresa){
		this.empresas.guardar(empresa);
	}
		
	@Transactional	
	public void excluir (Empresa empresa) throws NegocioException{
		empresa = this.empresas.porId(empresa.getId());
		
		this.empresas.remover(empresa);
	}
	 	

}

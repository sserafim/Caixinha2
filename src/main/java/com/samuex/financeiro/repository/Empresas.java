package com.samuex.financeiro.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.samuex.financeiro.model.Empresa;

public class Empresas implements Serializable {
	
	public static final long serialVersionUID=1L;
	
	private EntityManager manager;
	
	@Inject
	public Empresas (EntityManager manager){
		this.manager = manager;
	}	
	
	public Empresa porId(Long id){
		return manager.find(Empresa.class, id);
	}
	
	public List<Empresa> todas() {
		TypedQuery<Empresa> query = manager.createQuery(
				"from Empresa", Empresa.class);
		return query.getResultList();
	}
		
	public Empresa guardar (Empresa empresa){
		return this.manager.merge(empresa);	
	}
	
	public void remover(Empresa empresa){
		this.manager.remove(empresa);		
	}

}

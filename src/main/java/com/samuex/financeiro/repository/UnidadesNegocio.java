package com.samuex.financeiro.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.samuex.financeiro.model.UnidadeNegocio;

public class UnidadesNegocio implements Serializable {
	
	public static final long serialVersionUID=1L;
	
	private EntityManager manager;
	
	@Inject
	public UnidadesNegocio (EntityManager manager){
		this.manager = manager;
	}
	
	public UnidadeNegocio porId (Long codigo) {
		return manager.find(UnidadeNegocio.class, codigo);
	}
	
	public List<UnidadeNegocio> todas(){
		TypedQuery<UnidadeNegocio> query = manager.createQuery(
				"from UnidadeNegocio", UnidadeNegocio.class);
		return query.getResultList();
	}
	
	public UnidadeNegocio guardar(UnidadeNegocio unidadeNegocio){
		return this.manager.merge(unidadeNegocio);
	}
	
	public void remover (UnidadeNegocio unidadeNegocio){
		this.manager.remove(unidadeNegocio);
	}
}

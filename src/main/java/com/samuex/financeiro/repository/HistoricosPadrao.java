package com.samuex.financeiro.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.samuex.financeiro.model.HistoricoPadrao;

public class HistoricosPadrao implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public HistoricosPadrao (EntityManager manager){
		this.manager = manager;
	}	
		
	public HistoricoPadrao porId(String id){
		return manager.find(HistoricoPadrao.class, id);
	}
	
	public List<HistoricoPadrao> todos(){
		TypedQuery<HistoricoPadrao> query = manager.createQuery(
				"from HistoricoPadrao", HistoricoPadrao.class);
		return query.getResultList();
	}
	
	public void deletarTodos(){
		Query query = manager.createNamedQuery("DELETE FROM HISTORICOPADRAO");
		query.executeUpdate();
	}
	
	public void adicionar(HistoricoPadrao historicoPadrao){
		this.manager.persist(historicoPadrao);
	}
	
	public HistoricoPadrao guardar (HistoricoPadrao historicoPadrao){
		return this.manager.merge(historicoPadrao);
	}
	
	public void remover (HistoricoPadrao historicoPadrao){
		this.manager.remove(historicoPadrao);
	}
	
}

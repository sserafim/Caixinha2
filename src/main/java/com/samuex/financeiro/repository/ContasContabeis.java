package com.samuex.financeiro.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.samuex.financeiro.model.ContaContabil;

public class ContasContabeis implements Serializable {
	
	public static final long serialVersionUID = 1L;

	private EntityManager manager;
	
	@Inject
	public ContasContabeis (EntityManager manager){
		this.manager = manager;
	}	
		
	
	public ContaContabil porId(Long id){
		return manager.find(ContaContabil.class, id);
	}
		
	public List<ContaContabil> todas(){
		TypedQuery<ContaContabil> query = manager.createQuery(
				"from ContaContabil", ContaContabil.class);
		return query.getResultList();

	}
	
	public void deletarTodas(){
		Query query = manager.createNativeQuery("DELETE FROM CONTACONTABIL");		
		query.executeUpdate();		
	}	
	
	public void adicionar (ContaContabil contaContabil){
		this.manager.persist(contaContabil);
	}
	
	
	public ContaContabil guardar (ContaContabil contaContabil){
		return this.manager.merge(contaContabil);
	}
	
	public void remover (ContaContabil contaContabil ){
		this.manager.remove(contaContabil);
	}
		
}

package com.samuex.financeiro.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.samuex.financeiro.controller.Usuario;
import com.samuex.financeiro.model.LancamentoSaque;

public class LancamentosSaques implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	private Usuario usuarioLocal;
	
	@Inject
	public LancamentosSaques(EntityManager manager) {
		this.manager = manager;
	}
	
	public LancamentoSaque porId(Long id) {
		return manager.find(LancamentoSaque.class, id);
	}
	
	
	public List<LancamentoSaque> todos() {
		TypedQuery<LancamentoSaque> query = manager.createQuery(
				"from LancamentoSaque", LancamentoSaque.class);
		return query.getResultList();
	}
	
	public List<LancamentoSaque> buscaPorUnidade(){
		TypedQuery<LancamentoSaque> query = manager.createQuery("from LancamentoSaque where upper(localSaque) like upper(:localSaque)", LancamentoSaque.class);
		query.setParameter("localSaque", "%" + usuarioLocal.getLocalUsuario() + "%");
		return query.getResultList();
	}
	
	
	public void adicionar(LancamentoSaque lancamentoSaque){
		this.manager.persist(lancamentoSaque);
	}
	
	
	public LancamentoSaque guardar (LancamentoSaque lancamentoSaque){
		return this.manager.merge(lancamentoSaque);
	}
	
	public void remover(LancamentoSaque lancamentoSaque){
		this.manager.remove(lancamentoSaque);
	}
	
	
	

}

package com.samuex.financeiro.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.samuex.financeiro.controller.Usuario;
import com.samuex.financeiro.model.LancamentoSaqueDespesa;

public class LancamentosSaquesDespesas implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	private Usuario usuarioLocal;

	@Inject
	public LancamentosSaquesDespesas(EntityManager manager) {
		this.manager = manager;
	}
	
	public LancamentoSaqueDespesa porId(Long id) {
		return manager.find(LancamentoSaqueDespesa.class, id);
	}
	

	public List<LancamentoSaqueDespesa> todos() {
		TypedQuery<LancamentoSaqueDespesa> query = manager.createQuery(
				"from LancamentoSaqueDespesa", LancamentoSaqueDespesa.class);
		return query.getResultList();
	}
	
	public List<LancamentoSaqueDespesa> buscaPorUnidade(){
		TypedQuery<LancamentoSaqueDespesa> query = manager.createQuery(
				"from LancamentoSaqueDespesa where upper(localSaque) like upper(:localSaque)", LancamentoSaqueDespesa.class);
		query.setParameter("localSaque", "%" + usuarioLocal.getLocalUsuario() + "%");
		return query.getResultList();
	}
	

	public void adicionar(LancamentoSaqueDespesa lancamentoSaqueDespesa){
		this.manager.persist(lancamentoSaqueDespesa);
	}
	
	
	public LancamentoSaqueDespesa guardar (LancamentoSaqueDespesa lancamentoSaqueDespesa){
		return this.manager.merge(lancamentoSaqueDespesa);
	}
	
	public void remover(LancamentoSaqueDespesa lancamentoSaqueDespesa){
		this.manager.remove(lancamentoSaqueDespesa);
	}
	
	
	

}

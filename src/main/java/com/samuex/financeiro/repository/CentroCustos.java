package com.samuex.financeiro.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.samuex.financeiro.controller.Usuario;
import com.samuex.financeiro.model.CentroCusto;

public class CentroCustos implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	
	@Inject
	private Usuario usuarioLocal;
	
	@Inject
	public CentroCustos (EntityManager manager){
		this.manager = manager;
	}	
		
	public CentroCusto porId(String id){
		return manager.find(CentroCusto.class, id);
	}	
	
	public List<String> descricaoQueContem(String descricao){
		TypedQuery<String> query = manager.createQuery(
				"select distinct descricao from CentroCusto "
				+ "where upper(descricao) like upper(:descricao)",
				String.class);
		
		query.setParameter("descricao", "%" + descricao + "%");
		return query.getResultList();
	}
		
	public List<CentroCusto> todas() {
		TypedQuery<CentroCusto> query = manager.createQuery(
				"from CentroCusto "
				+ "order by unidadeNegocio.codigo asc", CentroCusto.class);
		return query.getResultList();
	}
	
	public String buscaCCusto(){
		TypedQuery<String> query = manager.createQuery(
				"select min(codigoGc) from CentroCusto " 
				+ "where unidadeNegocio.codigo = :unidadeCC",String.class);
		query.setParameter("unidadeCC", this.usuarioLocal.getUnidadeNegocio());
		return query.getSingleResult();
	}
	
	public List<CentroCusto> listPorUnidade(){
		TypedQuery<CentroCusto> query = manager.createQuery(
				"from CentroCusto where unidadeNegocio.codigo = :unidadeCC", CentroCusto.class);
		query.setParameter("unidadeCC", this.usuarioLocal.getUnidadeNegocio());
		return query.getResultList();
	}
	
	
	
	public void adicionar(CentroCusto centroCusto){
		this.manager.persist(centroCusto);
	}
		
	public CentroCusto guardar (CentroCusto centroCusto){
		return this.manager.merge(centroCusto);	
	}
	
	public void remover(CentroCusto centroCusto){
		this.manager.remove(centroCusto);		
	}	
	
	
	

}

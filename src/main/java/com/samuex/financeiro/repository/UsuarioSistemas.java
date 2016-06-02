package com.samuex.financeiro.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.samuex.financeiro.model.UsuarioSistema;

public class UsuarioSistemas implements Serializable{
	
	public static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	
	@Inject 
	public UsuarioSistemas(EntityManager manager){
		this.manager = manager;
	}
	
	public UsuarioSistema porID(Long id){
		return manager.find(UsuarioSistema.class, id);
	}
	
	public List<UsuarioSistema> todos(){
		TypedQuery<UsuarioSistema> query = manager.createQuery("from UsuarioSistema", UsuarioSistema.class);
		return query.getResultList();	
	}
	
	
	public String buscaLogin(String login) {
		TypedQuery<String> query = manager.createQuery(
				"select loginUsuario from UsuarioSistema "
				+ "where upper(loginUsuario) = upper(:loginUsuario)", 
				String.class);
		query.setParameter("loginUsuario",login);
		return query.getSingleResult();
	}

	public String buscaLocal(String login) {
		TypedQuery<String> query = manager.createQuery(
				"select concat(e.empresa.razaoSocial,' - ',e.nomeUnidade) from UsuarioSistema u inner join u.unidadeNegocio e "
				+ "where upper(loginUsuario) = upper(:loginUsuario)", 
				String.class);
		query.setParameter("loginUsuario",login);
		return query.getSingleResult();
	}
	
	public void adicionar(UsuarioSistema usuarioSistema){
		this.manager.persist(usuarioSistema);
	}
	 
	public UsuarioSistema guardar(UsuarioSistema usuario){
		return this.manager.merge(usuario); 
	}
	
	public void remover (UsuarioSistema usuario){
		this.manager.remove(usuario);
	}

}

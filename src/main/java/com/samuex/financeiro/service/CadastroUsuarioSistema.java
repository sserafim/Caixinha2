package com.samuex.financeiro.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.samuex.financeiro.model.UsuarioSistema;
import com.samuex.financeiro.repository.UsuarioSistemas;
import com.samuex.financeiro.util.Transactional;

public class CadastroUsuarioSistema implements Serializable{
	
	public static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioSistemas usuarioSistemas;
	
	@Transactional
	public void salvar(UsuarioSistema usuario) throws NegocioException {
		this.usuarioSistemas.guardar(usuario);
	}
		
	@Transactional
	public void excluir(UsuarioSistema usuario) throws NegocioException{
		usuario = this.usuarioSistemas.porID(usuario.getId());
		
		this.usuarioSistemas.remover(usuario);
	}
	
}

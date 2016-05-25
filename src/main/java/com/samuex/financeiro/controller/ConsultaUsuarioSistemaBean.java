package com.samuex.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.samuex.financeiro.model.UsuarioSistema;
import com.samuex.financeiro.repository.UsuarioSistemas;
import com.samuex.financeiro.service.CadastroUsuarioSistema;
import com.samuex.financeiro.service.NegocioException;

@Named
@ViewScoped
public class ConsultaUsuarioSistemaBean implements Serializable{
	public static final long serialVersionUID = 1L;
	
	
	@Inject
	private UsuarioSistemas usuariosSistemas;
	
	@Inject
	private CadastroUsuarioSistema cadastro;
	
	private List<UsuarioSistema> lancamentosUsuarios;
	
	private UsuarioSistema usuarioSelecionado;
	
	
	public void excluir(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		try{
			this.cadastro.excluir(this.usuarioSelecionado);
			context.addMessage( null, new FacesMessage("Usuário Excluído com sucesso!!"));
		}catch (NegocioException e){
			
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);						
		}
		
	}
	
	public void consulta(){
		this.lancamentosUsuarios = usuariosSistemas.todos();
	}
	
	
	public List<UsuarioSistema> getUsuarioSistema(){
		return lancamentosUsuarios;
	}

	public UsuarioSistema getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(UsuarioSistema usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

		
}

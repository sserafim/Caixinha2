package com.samuex.financeiro.controller;

import java.io.IOException;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import com.samuex.financeiro.repository.UsuarioSistemas;

@Named
@RequestScoped
public class LoginBean {

	@Inject
	private Usuario usuario;

	@Inject
	private UsuarioSistemas usuarios;

	private String nomeUsuario;
	private String senha;

	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();

		if ("COMPELL".equals(this.nomeUsuario) && "123".equals(this.senha)) {

			this.usuario.setNome(this.nomeUsuario);
			this.usuario.setDataLogin(new Date());

			return "/HomeCaixinha?faces-redirect=true";

		} else if (this.usuarios.buscaLogin(this.nomeUsuario).equals(this.nomeUsuario) && "123".equals(this.senha)) {

			this.usuario.setNome(this.nomeUsuario);
			this.usuario.setDataLogin(new Date());
			this.usuario.setPerfil(usuarios.getLogin(this.nomeUsuario).getTipoPerfil().getDescricao());
			this.usuario.setUnidadeNegocio(usuarios.getLogin(this.nomeUsuario).getUnidadeNegocio().getCodigo());
			this.usuario.setLocalUsuario(
					usuarios.getLogin(this.nomeUsuario).getUnidadeNegocio().getEmpresa().getRazaoSocial().concat(" - ")
							.concat(usuarios.getLogin(this.nomeUsuario).getUnidadeNegocio().getNomeUnidade()));

			return "/HomeCaixinha?faces-redirect=true";

		} else {
			FacesMessage mensagem = new FacesMessage("Usuário/senha inválidos!");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}

		return null;
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/Login?faces-redirect=true";
	}
	
	public void Home() {
		 FacesContext context = FacesContext.getCurrentInstance();
		    HttpServletRequest origRequest = (HttpServletRequest)context.getExternalContext().getRequest();
		    String contextPath = origRequest.getContextPath();
		try {
		        FacesContext.getCurrentInstance().getExternalContext()
		                .redirect(contextPath  + "/HomeCaixinha.xhtml");
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
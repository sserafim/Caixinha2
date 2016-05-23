package com.samuex.financeiro.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuarioSistema")
public class UsuarioSistema implements Serializable {

	private static final long serialVersionUID = 1L;
	
		private Long id;
		private String nomeCompleto;
		private String loginUsuario;
		private String empresa;
		private String unidade;
		private String senha;
		private TipoPerfil tipoPerfil;
		
		
		@Id
		@GeneratedValue
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		
		@Column(name = "nomeCompleto", nullable = true)
		public String getNomeCompleto() {
			return nomeCompleto;
		}
		
		public void setNomeCompleto(String nomeCompleto) {
			this.nomeCompleto = nomeCompleto.toUpperCase();
		}
				
		@Column(name = "loginUsuario", nullable = true)
		public String getLoginUsuario() {
			return loginUsuario;
		}
		
		public void setLoginUsuario(String loginUsuario) {
			this.loginUsuario = loginUsuario.toUpperCase();
		}
			
		@Column(name = "empresa", nullable = true)
		public String getEmpresa() {
			return empresa;
		}
		
		public void setEmpresa(String empresa) {
			this.empresa = empresa;
		}
		
		
		@Column(name = "unidade", nullable = true)
		public String getUnidade() {
			return unidade;
		}
		
		public void setUnidade(String unidade) {
			this.unidade = unidade;
		}
		
		
		@Column(name = "senha", nullable = true)
		public String getSenha() {
			return senha;
		}
		
		public void setSenha(String senha) {
			this.senha = senha.toUpperCase();
		}
		
		@Enumerated(EnumType.STRING)
		@Column(nullable = false)
		public TipoPerfil getTipoPerfil() {
			return tipoPerfil;
		}
		
		public void setTipoPerfil(TipoPerfil tipoPerfil) {
			this.tipoPerfil = tipoPerfil;
		}
		
				
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			UsuarioSistema other = (UsuarioSistema) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}

}

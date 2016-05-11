package com.samuex.financeiro.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "usuarioSistema")
public class UsuarioSistema implements Serializable {

	private static final long serialVersionUID = 1L;
	
		private Long id;
		private String nomeCompleto;
		private String loginUsuario;
		private int empresa;
		private String unidade;
		private String senha;
		private int administrator;
		
		
		@Id
		@GeneratedValue
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		@NotNull
		@Column(name = "nomeCompleto", nullable = true)
		public String getNomeCompleto() {
			return nomeCompleto;
		}
		
		public void setNomeCompleto(String nomeCompleto) {
			this.nomeCompleto = nomeCompleto;
		}
		
		@NotNull
		@Column(name = "loginUsuario", nullable = true)
		public String getLoginUsuario() {
			return loginUsuario;
		}
		
		public void setLoginUsuario(String loginUsuario) {
			this.loginUsuario = loginUsuario;
		}
		
		@NotNull
		@Column(name = "empresa", nullable = true)
		public int getEmpresa() {
			return empresa;
		}
		
		public void setEmpresa(int empresa) {
			this.empresa = empresa;
		}
		
		@NotNull
		@Column(name = "unidade", nullable = true)
		public String getUnidade() {
			return unidade;
		}
		
		public void setUnidade(String unidade) {
			this.unidade = unidade;
		}
		
		@NotNull
		@Column(name = "senha", nullable = true)
		public String getSenha() {
			return senha;
		}
		
		public void setSenha(String senha) {
			this.senha = senha;
		}
		
		@NotNull
		@Column(name = "administrator", nullable = true)
		public int getAdministrator() {
			return administrator;
		}
				
		public void setAdministrator(int administrator) {
			this.administrator = administrator;
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

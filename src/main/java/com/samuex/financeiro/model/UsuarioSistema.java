package com.samuex.financeiro.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "usuarioSistema")
public class UsuarioSistema implements Serializable {

	private static final long serialVersionUID = 1L;
	
		private Long id;
		private String nomeCompleto;
		private String loginUsuario;
		private UnidadeNegocio unidadeNegocio;
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
		
		@NotNull
		@ManyToOne(optional = false)
		@JoinColumn(name = "unidadeNegocio_id")		
		public UnidadeNegocio getUnidadeNegocio() {
			return unidadeNegocio;
		}
		public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
			this.unidadeNegocio = unidadeNegocio;
		}
		
		@NotEmpty
		@Size(max = 100)
		@Column(length = 100, name = "nomeCompleto", nullable = true)
		public String getNomeCompleto() {
			return nomeCompleto;
		}
		public void setNomeCompleto(String nomeCompleto) {
			this.nomeCompleto = nomeCompleto.toUpperCase();
		}
		
		@NotEmpty
		@Size(max = 50)
		@Column(length = 50, name = "loginUsuario", nullable = true)
		public String getLoginUsuario() {
			return loginUsuario;
		}
		
		public void setLoginUsuario(String loginUsuario) {
			this.loginUsuario = loginUsuario.toUpperCase();
		}			

		@NotEmpty
		@Size(max = 12)
		@Column(length = 12, name = "senha", nullable = true)
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

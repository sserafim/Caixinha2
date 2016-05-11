package com.samuex.financeiro.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;



@Entity
@Table(name = "empresa")
public class Empresa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String codigo;	
	private String nome;
	private String empresa;
	private String nomeUnidade;	
	private String CNPJ;
	private String inscEst;
	private String emailApoioLocal;
	
	
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotEmpty
	@Column(length = 2, nullable = false, unique = true)	
	public String getCodigo() {
		return codigo;
	}
		
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@NotEmpty
	@Size(max = 80)
	@Column(length = 80, nullable = false)	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}

	@NotEmpty
	@Column(length = 3, nullable = false)
	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	@NotEmpty
	@Size(max = 80)
	@Column(length = 80, nullable = false)	
	public String getNomeUnidade() {
		return nomeUnidade;
	}
	
	
	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade.toUpperCase();
	}

	@NotEmpty
	@Size(max = 14)
	@Column(length = 14, nullable = false)
	public String getCNPJ() {
		return CNPJ;
	}
	
	public void setCNPJ(String CNPJ) {
		this.CNPJ = CNPJ;
	}

	@NotEmpty
	@Size(max = 20)		
	@Column(length = 20, nullable = false)
	public String getInscEst() {
		return inscEst;
	}

	public void setInscEst(String inscEst) {
		this.inscEst = inscEst;
	}
	
	@NotEmpty
	@Size(max = 100)
	@Column(length = 100, nullable = false)
	public String getEmailApoioLocal() {
		return emailApoioLocal;
	}

	public void setEmailApoioLocal(String emailApoioLocal) {
		this.emailApoioLocal = emailApoioLocal.toLowerCase();
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
		Empresa other = (Empresa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

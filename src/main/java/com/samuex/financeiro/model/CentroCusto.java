package com.samuex.financeiro.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "centrocustos")
public class CentroCusto implements Serializable {
	
	public static final long serialVersionUID = 1L;
		
	private String codigo;
	private UnidadeNegocio unidadeNegocio;
	private String descricao;
	private String codigoGc;
	private String codigoGcReduzido;
	private Date dataExclusao;	

	@Id
	@Column(length = 6, nullable = false)	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "unidadeNegocio")
	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	@Column(length = 78, nullable = false)	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(length = 5, nullable = false)	
	public String getCodigoGc() {
		return codigoGc;
	}

	public void setCodigoGc(String codigoGc) {
		this.codigoGc = codigoGc;
	}
	
	@Column(length = 3, nullable = false)	
	public String getCodigoGcReduzido() {
		return codigoGcReduzido;
	}

	public void setCodigoGcReduzido(String codigoGcReduzido) {
		this.codigoGcReduzido = codigoGcReduzido;
	}

	@Temporal(TemporalType.DATE)
	@Column(length = 2)	
	public Date getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		CentroCusto other = (CentroCusto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
}

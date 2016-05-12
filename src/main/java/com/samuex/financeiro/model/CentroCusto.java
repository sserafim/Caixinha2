package com.samuex.financeiro.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "centrocusto")
public class CentroCusto implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	
	private Long id;
	private String codigo;
	private Empresa empresa;
	private String descricao;
	private String codigoGc;
	private String CodigoGcReduzido;
	private Date dataExclusao;	

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
	@JoinColumn(name = "empresa_id")
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@NotEmpty
	@Column(length = 6, nullable = false)	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
		return CodigoGcReduzido;
	}

	public void setCodigoGcReduzido(String codigoGcReduzido) {
		CodigoGcReduzido = codigoGcReduzido;
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
		CentroCusto other = (CentroCusto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}

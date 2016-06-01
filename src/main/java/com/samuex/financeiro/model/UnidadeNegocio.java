package com.samuex.financeiro.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;



@Entity
@Table(name = "unidadenegocio")
public class UnidadeNegocio implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private Long codigo;	
	private String nomeUnidade;	
	private Empresa empresa;
	private String CNPJ;
	private String inscEst;
	private BigDecimal saldoInicial;
	private BigDecimal saldoAtual;
	private String usuarioManutencao;
	private String emailApoioLocal;
	
	
	
	@Id
	@Column(length = 3, nullable = false)
	public Long getCodigo() {
		return codigo;
	}	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "empresa_id")
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	@Column(precision = 10, scale = 2, nullable = false)	
	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}
	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}
	
	@Column(precision = 10, scale = 2, nullable = false)
	public BigDecimal getSaldoAtual() {
		return saldoAtual;
	}
	public void setSaldoAtual(BigDecimal saldoAtual) {
		this.saldoAtual = saldoAtual;
	}
	
	@NotEmpty
	@NotNull
	@Column(length = 100, nullable = false)
	public String getUsuarioManutencao() {
		return usuarioManutencao;
	}
	public void setUsuarioManutencao(String usuarioManutencao) {
		this.usuarioManutencao = usuarioManutencao;
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
		UnidadeNegocio other = (UnidadeNegocio) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}

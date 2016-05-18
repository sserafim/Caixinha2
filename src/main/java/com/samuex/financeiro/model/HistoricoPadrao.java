package com.samuex.financeiro.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;



@Entity
@Table(name = "HistoricoPadrao")
public class HistoricoPadrao implements Serializable {
	
	public static final long serialVersionUID = 1L;

	@Id
	@Column(length = 5, nullable = false)	
	private String codHistorico;

	
	@NotNull
	@NotEmpty
	@Enumerated(EnumType.ORDINAL)
	@Column(length = 2,nullable = false)
	private TipoLote 	tipoLote;
	
	@NotNull
	@NotEmpty
	@Column(length = 100, nullable = false)	
	private String 		utilizacao;
	
	@OneToOne(optional = false)
	@JoinColumn(name = "contaContabil_ctaCtbCred")
	private ContaContabil ctaCtbCred;
	
	@OneToOne(optional = false)
	@JoinColumn(name = "contaContabil_ctaCtbDeb")
	private ContaContabil ctaCtbDeb;
	
	@NotNull
	@NotEmpty
	@Column(length = 3, nullable = false)
	private String 	CodHistCred;
	
	@NotNull
	@NotEmpty
	@Column(length = 3, nullable = false)
	private String 	CodHistDeb;
	
	@Column(length = 1, nullable = false)
	private String 		Historico;
	

	public String getCodHistorico() {
		return codHistorico;
	}
	public void setCodHistorico(String codHistorico) {
		this.codHistorico = codHistorico;
	}
	public TipoLote getTipoLote() {
		return tipoLote;
	}
	public void setTipoLote(TipoLote tipoLote) {
		this.tipoLote = tipoLote;
	}
	public ContaContabil getCtaCtbCred() {
		return ctaCtbCred;
	}
	public void setCtaCtbCred(ContaContabil ctaCtbCred) {
		this.ctaCtbCred = ctaCtbCred;
	}
	public ContaContabil getCtaCtbDeb() {
		return ctaCtbDeb;
	}
	public void setCtaCtbDeb(ContaContabil ctaCtbDeb) {
		this.ctaCtbDeb = ctaCtbDeb;
	}
	public String getUtilizacao() {
		return utilizacao;
	}
	public void setUtilizacao(String utilizacao) {
		this.utilizacao = utilizacao;
	}
	public String getCodHistCred() {
		return CodHistCred;
	}
	public void setCodHistCred(String codHistCred) {
		CodHistCred = codHistCred;
	}
	public String getCodHistDeb() {
		return CodHistDeb;
	}
	public void setCodHistDeb(String codHistDeb) {
		CodHistDeb = codHistDeb;
	}		
	public String getHistorico() {
		return Historico;
	}	
	public void setHistorico(String historico) {
		Historico = historico;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codHistorico == null) ? 0 : codHistorico.hashCode());
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
		HistoricoPadrao other = (HistoricoPadrao) obj;
		if (codHistorico == null) {
			if (other.codHistorico != null)
				return false;
		} else if (!codHistorico.equals(other.codHistorico))
			return false;
		return true;
	}
		
}

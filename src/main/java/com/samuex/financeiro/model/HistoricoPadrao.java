package com.samuex.financeiro.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name = "HistoricoPadrao")
public class HistoricoPadrao implements Serializable {
	
	public static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(generator = "inc")
	@GenericGenerator(name ="inc" , strategy = "increment")
	private Long 		id;
	
	
	private TipoLote 	tipoLote;
	private String 		utilizacao;
	private Integer 	codHistorico;
	private Integer 	CtaCtbCred;
	private Integer 	CtaCtbDeb;
	private Integer 	CodHistCred;
	private Integer 	CodHistDeb;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public TipoLote getTipoLote() {
		return tipoLote;
	}
	public void setTipoLote(TipoLote tipoLote) {
		this.tipoLote = tipoLote;
	}
	public String getUtilizacao() {
		return utilizacao;
	}
	public void setUtilizacao(String utilizacao) {
		this.utilizacao = utilizacao;
	}
	public Integer getCodHistorico() {
		return codHistorico;
	}
	public void setCodHistorico(Integer codHistorico) {
		this.codHistorico = codHistorico;
	}
	public Integer getCtaCtbCred() {
		return CtaCtbCred;
	}
	public void setCtaCtbCred(Integer ctaCtbCred) {
		CtaCtbCred = ctaCtbCred;
	}
	public Integer getCtaCtbDeb() {
		return CtaCtbDeb;
	}
	public void setCtaCtbDeb(Integer ctaCtbDeb) {
		CtaCtbDeb = ctaCtbDeb;
	}
	public Integer getCodHistCred() {
		return CodHistCred;
	}
	public void setCodHistCred(Integer codHistCred) {
		CodHistCred = codHistCred;
	}
	public Integer getCodHistDeb() {
		return CodHistDeb;
	}
	public void setCodHistDeb(Integer codHistDeb) {
		CodHistDeb = codHistDeb;
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
		HistoricoPadrao other = (HistoricoPadrao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
}

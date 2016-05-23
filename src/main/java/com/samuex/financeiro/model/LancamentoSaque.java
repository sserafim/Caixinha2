package com.samuex.financeiro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "lancamentosaque")
public class LancamentoSaque implements Serializable{
	
	public static final long serialVersionUID = 1L;
	
	private Long 			id;
	private Long			numeroSaque;
	private BigDecimal 		valorSaque;
	private Date 			DataLancamento;
	private TipoSaque 		tipo;
	private Long 			LocalLancamento; 
	
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotNull
	public Long getNumeroSaque() {
		return numeroSaque;
	}
	
	public void setNumeroSaque(Long numeroSaque) {
		this.numeroSaque = numeroSaque;
	}
	
	
	@Column(precision = 10, scale = 2, nullable = false)
	public BigDecimal getValorSaque() {
		return valorSaque;
	}
	
	public void setValorSaque(BigDecimal valorSaque) {
		this.valorSaque = valorSaque;
	}
	
	
	@Column(name = "localLancamento", nullable = true)
	public Long getLocalLancamento() {
		return LocalLancamento;
	}

	public void setLocalLancamento(Long localLancamento) {
		LocalLancamento = localLancamento;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "dataLancamento", nullable = false)
	public Date getDataLancamento() {
		return DataLancamento;
	}
	
	public void setDataLancamento(Date dataLancamento) {
		DataLancamento = dataLancamento;
	}
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public TipoSaque getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoSaque tipo) {
		this.tipo = tipo;
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
		LancamentoSaque other = (LancamentoSaque) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	

}

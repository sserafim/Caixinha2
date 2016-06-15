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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "lancamentoSaqueDespesa")
public class LancamentoSaqueDespesa implements Serializable{
	
	public static final long serialVersionUID = 1L;
	
	private Long 			id;
	//Saques
	private String			numeroSaque;
	private BigDecimal 		valorLancamento;
	private Date 			dataLancamento;
	private TipoSaque 		tipoSaque;
	private String 			local;
	private String 			usuarioLancamento;
	private HistoricoPadrao historicoPadrao;

	private CentroCusto 	centroCusto;
	private String 			numeroNfCupon;
	private String 			nomeFornecedor;
	private String			observacao;
	private TipoLancamento 	tipoLancamento;

	

	@Id
	@GeneratedValue(generator = "inc")
	@GenericGenerator(name = "inc", strategy = "increment")
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
//----------------
	

	@Column(length = 20, nullable = true)
	public String getNumeroSaque() {
		return numeroSaque;
	}

	public void setNumeroSaque(String numeroSaque) {
		this.numeroSaque = numeroSaque;
	}
//----------------
	@Column(precision = 10, scale = 2, nullable = true)
	public BigDecimal getValorLancamento() {
		return valorLancamento;
	}

	public void setValorLancamento(BigDecimal valorLancamento) {
		this.valorLancamento = valorLancamento;
	}
//----------------

	@Temporal(TemporalType.DATE)
	@Column(name = "datalancamento", nullable = true)
	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
//----------------

	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	public TipoSaque getTipoSaque() {
		return tipoSaque;
	}

	public void setTipoSaque(TipoSaque tipoSaque) {
		this.tipoSaque = tipoSaque;
	}
//----------------
	
	@Column(length = 80, nullable = true)
	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
//----------------
	
	@Column(length = 80, nullable = true)
	public String getUsuarioLancamento() {
		return usuarioLancamento;
	}

	public void setUsuarioLancamento(String usuarioLancamento) {
		this.usuarioLancamento = usuarioLancamento;
	}

//----------------	

	@ManyToOne(optional = false)
	@JoinColumn(name = "historicoPadrao")
	public HistoricoPadrao getHistoricoPadrao() {
		return historicoPadrao;
	}

	public void setHistoricoPadrao(HistoricoPadrao historicoPadrao) {
		this.historicoPadrao = historicoPadrao;
	}	
//----------------	

	@ManyToOne(optional = false)
	@JoinColumn(name = "centroCusto")	
	public CentroCusto getCentroCusto() {
		return centroCusto;
	}

	public void setCentroCusto(CentroCusto centroCusto) {
		this.centroCusto = centroCusto;
	}
//----------------	
	@Column(length = 20, nullable = true)
	public String getNumeroNfCupon() {
		return numeroNfCupon;
	}

	public void setNumeroNfCupon(String numeroNfCupon) {
		this.numeroNfCupon = numeroNfCupon;
	}
//----------------	
	@Column(length = 100, nullable = true)
	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}
//----------------		
	@Column(length = 200, nullable = true)
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;

	}
//----------------		
	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	public TipoLancamento getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(TipoLancamento tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}
//----------------
	
	
	
	
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
		LancamentoSaqueDespesa other = (LancamentoSaqueDespesa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

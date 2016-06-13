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
	private BigDecimal 		valorSaque;
	private Date 			dataSaque;
	private TipoSaque 		tipoSaque;
	private String 			localSaque;
	private String 			usuarioSaque;
	private HistoricoPadrao historicoPadrao;
	//Despesas
	private CentroCusto 	centroCusto;
	private String 			numeroNfCupon;
	private String 			nomeFornecedor;
	private BigDecimal		valorDespesa;
	private String			observacao;
	

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
	public BigDecimal getValorSaque() {
		return valorSaque;
	}

	public void setValorSaque(BigDecimal valorSaque) {
		this.valorSaque = valorSaque;
	}
//----------------

	@Temporal(TemporalType.DATE)
	@Column(name = "datasaque", nullable = true)
	public Date getDataSaque() {
		return dataSaque;
	}

	public void setDataSaque(Date dataSaque) {
		this.dataSaque = dataSaque;
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
	public String getLocalSaque() {
		return localSaque;
	}

	public void setLocalSaque(String localSaque) {
		this.localSaque = localSaque;
	}
//----------------
	
	@Column(length = 80, nullable = true)
	public String getUsuarioSaque() {
		return usuarioSaque;
	}

	public void setUsuarioSaque(String usuarioSaque) {
		this.usuarioSaque = usuarioSaque;
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
	@JoinColumn(name = "CentroCusto")	
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
	@Column(precision = 10, scale = 2, nullable = true)
	public BigDecimal getValorDespesa() {
		return valorDespesa;
	}

	public void setValorDespesa(BigDecimal valorDespesa) {
		this.valorDespesa = valorDespesa;
	}
//----------------		
	@Column(length = 200, nullable = true)
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
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
		LancamentoSaqueDespesa other = (LancamentoSaqueDespesa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

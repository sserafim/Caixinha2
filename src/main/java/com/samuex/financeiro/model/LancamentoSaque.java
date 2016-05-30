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

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "lancamentosaque")
public class LancamentoSaque implements Serializable{
	
	public static final long serialVersionUID = 1L;
	
	private Long 			id;
	private String			numeroSaque;
	private BigDecimal 		valorSaque;
	private Date 			DataSaque;
	private TipoSaque 		tipoSaque;
	private String 			LocalSaque;
	private String 			usuarioSaque;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
//----------------
	
	@NotEmpty
	@Column(length = 20, nullable = false)
	public String getNumeroSaque() {
		return numeroSaque;
	}

	public void setNumeroSaque(String numeroSaque) {
		this.numeroSaque = numeroSaque;
	}
//----------------
	
	
	@Column(precision = 10, scale = 2, nullable = false)
	public BigDecimal getValorSaque() {
		return valorSaque;
	}

	public void setValorSaque(BigDecimal valorSaque) {
		this.valorSaque = valorSaque;
	}
//----------------

	@Temporal(TemporalType.DATE)
	@Column(name = "datasaque", nullable = false)
	public Date getDataSaque() {
		return DataSaque;
	}

	public void setDataSaque(Date dataSaque) {
		DataSaque = dataSaque;
	}
//----------------

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public TipoSaque getTipoSaque() {
		return tipoSaque;
	}

	public void setTipoSaque(TipoSaque tipoSaque) {
		this.tipoSaque = tipoSaque;
	}
//----------------
	
	@Column(length = 80, nullable = true)
	public String getLocalSaque() {
		return LocalSaque;
	}

	public void setLocalSaque(String localSaque) {
		LocalSaque = localSaque;
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

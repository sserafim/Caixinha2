package com.samuex.financeiro.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "ContaContabil")
public class ContaContabil implements Serializable{
	
	public static final long serialVersionUID = 1L;
	

	@Id
	@Column(length = 5, nullable = false)
	private String contaContabilRed;
	
	@NotEmpty
	@NotNull
	@Column(length = 8, nullable = false)
	private String contaContabilExp;
	
	@NotEmpty
	@NotNull
	@Column(length = 50, nullable = false)
	private String descricao;
	

	public String getContaContabilRed() {
		return contaContabilRed;
	}

	public void setContaContabilRed(String contaContabilRed) {
		this.contaContabilRed = contaContabilRed;
	}

	public String getContaContabilExp() {
		return contaContabilExp;
	}

	public void setContaContabilExp(String contaContabilExp) {
		this.contaContabilExp = contaContabilExp;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contaContabilRed == null) ? 0 : contaContabilRed.hashCode());
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
		ContaContabil other = (ContaContabil) obj;
		if (contaContabilRed == null) {
			if (other.contaContabilRed != null)
				return false;
		} else if (!contaContabilRed.equals(other.contaContabilRed))
			return false;
		return true;
	}

}

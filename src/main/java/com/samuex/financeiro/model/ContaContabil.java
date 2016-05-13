package com.samuex.financeiro.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "ContaContabil")
public class ContaContabil implements Serializable{
	
	public static final long serialVersionUID = 1L;
	
	private Long id;
	private String contaContabilExp;
	private String descricao;
	private String contaContabilRed;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotEmpty
	@NotNull
	@Column(length = 8, nullable = false)
	public String getContaContabilExp() {
		return contaContabilExp;
	}
	
	public void setContaContabilExp(String contaContabilExp) {
		this.contaContabilExp = contaContabilExp;
	}
	
	@NotEmpty
	@NotNull
	@Column(length = 50, nullable = false)
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@NotEmpty
	@NotNull
	@Column(length = 5, nullable = false)
	public String getContaContabilRed() {
		return contaContabilRed;
	}
	
	public void setContaContabilRed(String contaContabilRed) {
		this.contaContabilRed = contaContabilRed;
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
		ContaContabil other = (ContaContabil) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

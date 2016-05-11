package com.samuex.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.samuex.financeiro.model.Pessoa;
import com.samuex.financeiro.repository.Pessoas;

@Named
@ViewScoped
public class ConsultaPessoaBean implements Serializable {
	
	private static final long serialVersionUID=1L; 
	
	@Inject
	private Pessoas pessoaRepository;

	private List<Pessoa> pessoas;
	
	
	public void consultar(){
		this.pessoas = pessoaRepository.todas();
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}	
	
}

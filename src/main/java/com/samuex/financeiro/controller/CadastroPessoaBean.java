package com.samuex.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.samuex.financeiro.model.Pessoa;
import com.samuex.financeiro.repository.Pessoas;
import com.samuex.financeiro.service.CadastroPessoas;
import com.samuex.financeiro.service.NegocioException;

@Named
@javax.faces.view.ViewScoped
public class CadastroPessoaBean implements Serializable{
	
	private static final long serialVersionUID=1L;
	
	
	private Pessoa pessoa;

	@Inject
	private Pessoas pessoaRepository;
	
	private List<Pessoa> pessoas;
	
	public void prepararCadastro() {

		if (this.pessoa == null) {
			this.pessoa = new Pessoa();
		}
	}	
	
	@Inject
	private CadastroPessoas cadastro;
	
	public void salvar() throws NegocioException{
		
		FacesContext context = FacesContext.getCurrentInstance();
		this.cadastro.salvar(this.pessoa);
		
		context.addMessage(null, new FacesMessage("Lançamento salvo com sucesso!"));

	}
	
	public void consultar(){
		this.pessoas = pessoaRepository.todas();
	}
	
	public List<Pessoa> listPessoas(){
		return pessoaRepository.todas();
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}


}

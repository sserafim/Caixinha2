package com.samuex.financeiro.controller;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.samuex.financeiro.model.ContaContabil;
	
@ManagedBean
public class FileUploadView {

	private UploadedFile file;
	
	private String contaExpandida;
	private String descricao;
	private String contaReduzida;
	
	private ContaContabil contaContabil;

	String line = null;
	
	
	public void handleFileUpload(FileUploadEvent event) {
		
		try {
			carregaArquivo(event.getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public void carregaArquivo(UploadedFile file) throws IOException{
		
		BufferedReader buff = new BufferedReader(new InputStreamReader(file.getInputstream()));
		
		while((line = buff.readLine()) != null) {
		
				StringTokenizer st = new StringTokenizer(line, ",");		
				
				while (st.hasMoreElements()) {
					
					this.contaExpandida = st.nextElement().toString();
					this.descricao = st.nextElement().toString();
					this.contaReduzida = st.nextElement().toString();

				}				
				  //System.out.println(this.contaExpandida + " - "   + this.descricao + " - " + this.contaReduzida);
			}				
	}
	
	
	
	

	
	public String getContaExpandida() {
		return contaExpandida;
	}

	public void setContaExpandida(String contaExpandida) {
		this.contaExpandida = contaExpandida;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getContaReduzida() {
		return contaReduzida;
	}

	public void setContaReduzida(String contaReduzida) {
		this.contaReduzida = contaReduzida;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public ContaContabil getContaContabil() {
		return contaContabil;
	}

	public void setContaContabil(ContaContabil contaContabil) {
		this.contaContabil = contaContabil;
	}

	
	
}



    
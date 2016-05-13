package com.samuex.financeiro.controller;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.StringTokenizer;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.samuex.financeiro.model.ContaContabil;
import com.samuex.financeiro.service.CadastroCContabil;


@Named
@javax.faces.view.ViewScoped
public class FileUploadView implements Serializable{

	private static final long serialVersionUID = 1L;

	private UploadedFile file;
	
	private ContaContabil contaContabil;
	
	@Inject
	private CadastroCContabil cadastro;
	
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
					
					this.contaContabil = new ContaContabil();
					
					
					this.contaContabil.setContaContabilExp(st.nextElement().toString()); 
					this.contaContabil.setDescricao(st.nextElement().toString()); 
					this.contaContabil.setContaContabilRed(st.nextElement().toString());  

					salvar();
				}	
			}				
	}	
	
	
	public void salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		this.cadastro.salvar(this.contaContabil);
		context.addMessage(null, new FacesMessage("Conta(s) Contábil carregadas com sucesso!!"));		
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

    
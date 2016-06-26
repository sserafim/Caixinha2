package com.samuex.financeiro.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
public class ReturnMessage {
	public static final String erro = "erro";
	public static final String info = "info";
	public static final String alert = "alert";
	public static final String criticalError = "criticalError";
	public static void setMessage(String msg, String tipo) {
		if (tipo.equalsIgnoreCase("erro")) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
		}
		if (tipo.equalsIgnoreCase("info")) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
		}
		if (tipo.equalsIgnoreCase("alert")) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, msg, null));
		}
		if (tipo.equalsIgnoreCase("criticalError")) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, null));
		}
	}
}
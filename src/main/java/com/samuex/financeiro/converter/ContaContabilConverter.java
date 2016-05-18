package com.samuex.financeiro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.samuex.financeiro.model.ContaContabil;
import com.samuex.financeiro.repository.ContasContabeis;

@FacesConverter(forClass = ContaContabil.class)
public class ContaContabilConverter implements Converter{ 

	@Inject
	private ContasContabeis contasContabeis;
		
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		ContaContabil retorno = null;
		
		if (value != null && !"".equals(value)) {
			retorno = this.contasContabeis.porId(new String(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			ContaContabil contaContabil = ((ContaContabil) value); 
			return contaContabil.getContaContabilRed() == null ? null : contaContabil.getContaContabilRed().toString();
		}
		return null;
	}
		
}

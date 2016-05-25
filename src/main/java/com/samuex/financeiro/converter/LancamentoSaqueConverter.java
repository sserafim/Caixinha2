package com.samuex.financeiro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.samuex.financeiro.model.LancamentoSaque;
import com.samuex.financeiro.repository.LancamentosSaques;


@FacesConverter(forClass = LancamentoSaque.class)
public class LancamentoSaqueConverter implements Converter {
	
	@Inject
	private LancamentosSaques lancamentosSaques;
	
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		LancamentoSaque retorno = null;
		
		if (value != null && !"".equals(value)) {
			retorno = this.lancamentosSaques.porId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			LancamentoSaque lancamentoSaque = ((LancamentoSaque) value); 
			return lancamentoSaque.getId() == null ? null : lancamentoSaque.getId().toString();
		}
		return null;
	}	
	
	
	
	
	
	

}

package com.samuex.financeiro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.samuex.financeiro.model.LancamentoSaqueDespesa;
import com.samuex.financeiro.repository.LancamentosSaquesDespesas;


@FacesConverter(forClass = LancamentoSaqueDespesa.class)
public class LancamentoSaqueDespesaConverter implements Converter {
	
	@Inject
	private LancamentosSaquesDespesas lancamentosSaques;
	
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		LancamentoSaqueDespesa retorno = null;
		
		if (value != null && !"".equals(value)) {
			retorno = this.lancamentosSaques.porId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			LancamentoSaqueDespesa lancamentoSaque = ((LancamentoSaqueDespesa) value); 
			return lancamentoSaque.getId() == null ? null : lancamentoSaque.getId().toString();
		}
		return null;
	}	
	
	
	
	
	
	

}

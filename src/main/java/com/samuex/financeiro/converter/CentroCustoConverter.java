package com.samuex.financeiro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.samuex.financeiro.model.CentroCusto;
import com.samuex.financeiro.repository.CentroCustos;

@FacesConverter(forClass = CentroCusto.class)
public class CentroCustoConverter implements Converter{ 
	
	
	@Inject
	private CentroCustos centroCustos;
		
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		CentroCusto retorno = null;
		
		if (value != null && !"".equals(value)) {
			retorno = this.centroCustos.porId(value);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			CentroCusto centroCusto = ((CentroCusto) value); 
			return centroCusto.getCodigoGc() == null ? null : centroCusto.getCodigoGc().toString();
		}
		return null;
	}

}

package com.samuex.financeiro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.samuex.financeiro.model.UnidadeNegocio;
import com.samuex.financeiro.repository.UnidadesNegocio;


@FacesConverter(forClass = UnidadeNegocio.class)
public class UnidadeNegocioConverter implements Converter{
	
	@Inject
	private UnidadesNegocio unidadesNegocio;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		UnidadeNegocio retorno = null;
		
		if (value != null && !"".equals(value)) {
			retorno = this.unidadesNegocio.porId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			UnidadeNegocio unidadeNegocio = ((UnidadeNegocio) value); 
			return unidadeNegocio.getCodigo() == null ? null : unidadeNegocio.getCodigo().toString();
		}
		return null;
	}
	

}

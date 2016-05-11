package com.samuex.financeiro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.samuex.financeiro.model.Empresa;
import com.samuex.financeiro.repository.Empresas;


@FacesConverter(forClass = Empresa.class)
public class EmpresaConverter implements Converter{
	
	@Inject
	private Empresas empresas;
		
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Empresa retorno = null;
		
		if (value != null && !"".equals(value)) {
			retorno = this.empresas.porId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Empresa empresa = ((Empresa) value); 
			return empresa.getId() == null ? null : empresa.getId().toString();
		}
		return null;
	}

}

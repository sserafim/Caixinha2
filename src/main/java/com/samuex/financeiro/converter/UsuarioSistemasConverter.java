package com.samuex.financeiro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.samuex.financeiro.model.UsuarioSistema;
import com.samuex.financeiro.repository.UsuarioSistemas;



@FacesConverter(forClass = UsuarioSistema.class)
public class UsuarioSistemasConverter implements Converter {
	
	@Inject
	private UsuarioSistemas usuarioSistemas;
	
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		UsuarioSistema retorno = null;
		
		if (value != null && !"".equals(value)) {
			retorno = this.usuarioSistemas.porID(new Long(value));
			
		}

		return retorno;
	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			
			UsuarioSistema usuarioSistema = ((UsuarioSistema) value);
			return usuarioSistema.getId() == null ? null : usuarioSistema.getId().toString();					
					
		}
		return null;
	}

}

package com.samuex.financeiro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.samuex.financeiro.model.HistoricoPadrao;
import com.samuex.financeiro.repository.HistoricosPadrao;

@FacesConverter(forClass = HistoricoPadrao.class)
public class HistPadraoConverter implements Converter{
	
	@Inject
	private HistoricosPadrao historicosPadrao;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		HistoricoPadrao retorno = null;
		
		if (value != null && !"".equals(value)) {
			retorno = this.historicosPadrao.porId(new String(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			HistoricoPadrao historicoPadrao = ((HistoricoPadrao) value); 
			return historicoPadrao.getCodHistorico() == null ? null : historicoPadrao.getCodHistorico().toString();
		}
		return null;
	}


}

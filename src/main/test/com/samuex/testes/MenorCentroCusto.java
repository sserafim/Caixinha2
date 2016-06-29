package com.samuex.testes;

import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.samuex.financeiro.controller.Usuario;
import com.samuex.financeiro.model.CentroCusto;
import com.samuex.financeiro.util.EntityManagerProducer;


@RunWith(MockitoJUnitRunner.class)
public class MenorCentroCusto {
	
	@InjectMocks
	private EntityManagerProducer manager;
	
	private Long unidade = (long) 9;
	
	@InjectMocks
	private Usuario usuarioLocal;
	
	@Test
	public void buscaCCustoGeral(){
		
		TypedQuery<CentroCusto> query = manager.createEntityManager().createQuery(
				"from CentroCusto " 
				+ "where codigoGc = (select min(codigoGc) from CentroCusto where unidadeNegocio.codigo = :unidadeCC )",CentroCusto.class);
		query.setParameter("unidadeCC", unidade);
		
		CentroCusto resultado =  query.getSingleResult();
		
		System.out.println(resultado.getCodigoGc());
		
	//	List<CentroCusto> resultado = query.getResultList();		
	//	for(CentroCusto valores : resultado){		
	//	System.out.println(valores.getCodigoGc() + " - " + valores.getCodigoGcReduzido());	
	//	}

}
	
}

package com.samuex.testes;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.samuex.financeiro.util.EntityManagerProducer;



@RunWith(MockitoJUnitRunner.class)
public class retornoAgrupamento{

	
	@InjectMocks
	private EntityManagerProducer manager;

	@Test
	public void buscaLancamentoHome(){
			
		TypedQuery<Object[]> query = manager.createEntityManager().createQuery(
				"select dataLancamento, sum(case when tipoLancamento = 'SAQUE' then valorLancamento else 0.00 end) as SAQUE, sum(case when tipoLancamento = 'DESPESA' then valorLancamento else 0.00 end) as DESPESA "
				+ "from LancamentoSaqueDespesa "
				+ "where local like upper (:localHome) "
				+ "group by dataLancamento", Object[].class);
		query.setParameter("localHome", "%" + "LEOPOLDINA" + "%");
		
		List<Object[]> resultado = query.getResultList();
		
		for(Object[] valores : resultado){
			
			Date data = (Date) valores[0];
			BigDecimal saque = (BigDecimal) valores[1];
			BigDecimal despesa = (BigDecimal) valores[2];
			
			System.out.println(data + " - "  + saque + " - " + despesa);
		}
	}
}
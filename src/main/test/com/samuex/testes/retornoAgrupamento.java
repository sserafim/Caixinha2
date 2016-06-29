package com.samuex.testes;

import java.math.BigDecimal;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public void buscaLancamentoHome() throws ParseException{
		
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		Date dtLancamento = fmt.parse("24/06/2016");
			
		TypedQuery<Object[]> query = manager.createEntityManager().createQuery(
				"select dataLancamento, "
				+ "local, "
				+ "sum(case when tipoLancamento = 'SAQUE' then valorLancamento else 0.00 end) as SAQUE, "
				+ "sum(case when tipoLancamento = 'DESPESA' then valorLancamento else 0.00 end) as DESPESA "
				+ "from LancamentoSaqueDespesa "
				+ "where local like upper (:localHome) "
				+ "and dataLancamento = (:dtLanc) "
				+ "group by dataLancamento, "
				+ "local", Object[].class);
		query.setParameter("localHome", "%" + "LEOPOLDINA" + "%");
		query.setParameter("dtLanc", dtLancamento);
		
		List<Object[]> resultado = query.getResultList();
		
		for(Object[] valores : resultado){
			
			Date data = (Date) valores[0];
			String local = (String) valores[1];
			BigDecimal saque = (BigDecimal) valores[2];
			BigDecimal despesa = (BigDecimal) valores[3];
			
			System.out.println(data + " - "  + saque + " - " + despesa + " - " + local);
		}
	}
}
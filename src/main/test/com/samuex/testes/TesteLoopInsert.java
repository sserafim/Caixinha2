package com.samuex.testes;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.samuex.financeiro.model.CentroCusto;
import com.samuex.financeiro.model.HistoricoPadrao;
import com.samuex.financeiro.model.LancamentoSaqueDespesa;
import com.samuex.financeiro.model.TipoLancamento;
import com.samuex.financeiro.service.NegocioException;
import com.samuex.financeiro.util.EntityManagerProducer;
import com.samuex.financeiro.util.Transactional;

@RunWith(MockitoJUnitRunner.class)
public class TesteLoopInsert {

	@InjectMocks
	private EntityManagerProducer manager;


	@Test
	public void buscaLancamentoHome() throws ParseException {

		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		Date dtLancamento = fmt.parse("24/06/2016");

		TypedQuery<LancamentoSaqueDespesa> query = manager.createEntityManager()
				.createQuery("from LancamentoSaqueDespesa " + "where local like upper (:localHome) "
						+ "and dataLancamento = (:dataLanc)", LancamentoSaqueDespesa.class);
		query.setParameter("localHome", "%" + "LEOPOLDINA" + "%");
		query.setParameter("dataLanc", dtLancamento);

		List<LancamentoSaqueDespesa> resultado = query.getResultList();
		
		buscaTotaisFechamentoCx();
		
		for(LancamentoSaqueDespesa valores : resultado){
			System.out.println(valores.getLocal() + " - "  + valores.getId() + " - " + valores.getUsuarioLancamento());
			
		}
	}
	
	public void buscaTotaisFechamentoCx() throws ParseException{
		
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		Date dtLancamento = fmt.parse("24/06/2016");
		
		TypedQuery<Object[]> query = manager.createEntityManager().createQuery(
				"select dataLancamento, "
				+ "local, "
				+ "sum(case when tipoLancamento = 'SAQUE' then valorLancamento else 0.00 end) as SAQUE, "
				+ "sum(case when tipoLancamento = 'DESPESA' then valorLancamento else 0.00 end) as DESPESA "
				+ "from LancamentoSaqueDespesa "
				+ "where local like upper (:localHome) "
				+ "and dataLancamento = (:dataLanc) "
				+ "group by dataLancamento, "
				+ "local", Object[].class);
		query.setParameter("localHome", "%" + "LEOPOLDINA" + "%");
		query.setParameter("dataLanc", dtLancamento);
	
		List<Object[]> resultado = query.getResultList();
		
		for(Object[] valores : resultado){
			
			Date data = (Date) valores[0];
			String local = (String) valores[1];
			BigDecimal saque = (BigDecimal) valores[2];
			BigDecimal despesa = (BigDecimal) valores[3];
			
			LancamentoSaqueDespesa lancamentoSaque = new LancamentoSaqueDespesa();
				
				
				lancamentoSaque.setDataLancamento(data);
				lancamentoSaque.setLocal(local);
				lancamentoSaque.setTipoLancamento(TipoLancamento.FECHAMENTO);
				lancamentoSaque.setValorLancamento(saque);

				this.guardar(lancamentoSaque);
				
				
		}
		
		
	}
	

	public void atualizaStatusIntegracao(Long idLanc) {
		LancamentoSaqueDespesa lancSaqueDespesa = this.porId(idLanc);
		lancSaqueDespesa.setStatusIntegracao("N");
		try {
			this.salvar(lancSaqueDespesa);

		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}

	public LancamentoSaqueDespesa porId(Long id) {
		return manager.createEntityManager().find(LancamentoSaqueDespesa.class, id);
	}
	
	public HistoricoPadrao porIdCC(String id){
		return manager.createEntityManager().find(HistoricoPadrao.class, id);
	}
	
	public CentroCusto porIdHist(String id){
		return manager.createEntityManager().find(CentroCusto.class, id);
	}	
	
	
	public void salvar(LancamentoSaqueDespesa lancamentoSaqueDespesa) throws NegocioException{
		
		this.guardar(lancamentoSaqueDespesa);		
	}
	
	@Transactional
	public void guardar (LancamentoSaqueDespesa lancamentoSaqueDespesa){
//		 this.manager.createEntityManager().merge(lancamentoSaqueDespesa);
		 EntityManager em = this.manager.createEntityManager();
		 em.getTransaction().begin();
		 em.merge(lancamentoSaqueDespesa);
		 em.getTransaction().commit();
		
		
	}
	
	
	
	
	
	
	
	
}
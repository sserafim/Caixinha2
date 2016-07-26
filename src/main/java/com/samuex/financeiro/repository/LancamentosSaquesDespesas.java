package com.samuex.financeiro.repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.samuex.financeiro.controller.Usuario;
import com.samuex.financeiro.model.LancamentoSaqueDespesa;
import com.samuex.financeiro.model.TipoLancamento;
import com.samuex.financeiro.service.NegocioException;

public class LancamentosSaquesDespesas implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	private String descHistoPadrao;
	private String numNfCupom;
	private String naturezaContabil;
	
	BufferedWriter Writer = null;
	
	@Inject
	private Usuario usuarioLocal;

	@Inject
	public LancamentosSaquesDespesas(EntityManager manager) {
		this.manager = manager;
	}
	
	public LancamentoSaqueDespesa porId(Long id) {
		return manager.find(LancamentoSaqueDespesa.class, id);
	}
	

	public List<LancamentoSaqueDespesa> todos() {
		TypedQuery<LancamentoSaqueDespesa> query = manager.createQuery(
				"from LancamentoSaqueDespesa", LancamentoSaqueDespesa.class);
		return query.getResultList();
	}
	
	public List<LancamentoSaqueDespesa> todosLancamDia(Date dataLanc) {
		TypedQuery<LancamentoSaqueDespesa> query = manager.createQuery(
				"from LancamentoSaqueDespesa "
				+ "where dataLancamento = (:dtLancamento)", LancamentoSaqueDespesa.class);		
		query.setParameter("dtLancamento", dataLanc);
		return query.getResultList();
	}

	
	public List<LancamentoSaqueDespesa> buscaPorUnidadeSaque(){
		TypedQuery<LancamentoSaqueDespesa> query = manager.createQuery(
				"from LancamentoSaqueDespesa "
				+ "where upper(local) like upper(:localSaque)"
				+ "and tipoLancamento = :tpLancamento "
				+ "and usuarioLancamento <> ('ADMIN') "
				+ "order by (dataLancamento) desc", LancamentoSaqueDespesa.class);
		query.setParameter("localSaque", "%" + usuarioLocal.getLocalUsuario() + "%");
		query.setParameter("tpLancamento", TipoLancamento.SAQUE);
		return query.getResultList();
	}
	
	public List<LancamentoSaqueDespesa> buscaPorUnidadeDespesa(){
		TypedQuery<LancamentoSaqueDespesa> query = manager.createQuery(
				"from LancamentoSaqueDespesa "
				+ "where upper(local) like upper(:localSaque)"
				+ "and tipoLancamento = :tpLancamento "
				+ "and usuarioLancamento <> ('ADMIN') "
				+ "order by (dataLancamento) desc", LancamentoSaqueDespesa.class);
		query.setParameter("localSaque", "%" + usuarioLocal.getLocalUsuario() + "%");
		query.setParameter("tpLancamento", TipoLancamento.DESPESA);
		return query.getResultList();
	}
	
	public List<Object[]> buscaLancamentosHome(){
		TypedQuery<Object[]> query = manager.createQuery(
				"select dataLancamento, "
				+ "sum(case when tipoLancamento = 'SAQUE' then valorLancamento else 0.00 end) as SAQUE, "
				+ "sum(case when tipoLancamento = 'DESPESA' then valorLancamento else 0.00 end) as DESPESA "
				+ "from LancamentoSaqueDespesa "
				+ "where local like upper (:localHome) "
				+ "and usuarioLancamento <> ('ADMIN') "
				+ "group by dataLancamento "
				+ "order by dataLancamento desc", Object[].class);
		query.setParameter("localHome","%" + usuarioLocal.getLocalUsuario() + "%");
	
		return query.getResultList();
	}
	
	public List<Object[]> buscaLancamentosHomeDetalhe(Date data){
		TypedQuery<Object[]> query = manager.createQuery(
				"select dataLancamento, "
				+ "(case when tipoLancamento = 'SAQUE' then valorLancamento end) as SAQUE, "
				+ "(case when tipoLancamento = 'DESPESA' then valorLancamento end) as DESPESA "
				+ "from LancamentoSaqueDespesa "
				+ "where local like upper (:localHome) "
				+ "and dataLancamento = (:dataLanc) "
				+ "and usuarioLancamento <> ('ADMIN') "
				+ "order by dataLancamento desc", Object[].class);
		query.setParameter("localHome", "%" + usuarioLocal.getLocalUsuario() + "%");
		query.setParameter("dataLanc", data);
	
		return query.getResultList();
	}
	
public void geraArquivoFechamentoCaixinha(Date dtLancamento) throws ParseException, NegocioException {
		
		SimpleDateFormat fmtString = new SimpleDateFormat("ddMMyyyy");

		TypedQuery<LancamentoSaqueDespesa> query = manager.createQuery(
						"from LancamentoSaqueDespesa "
								+ "where local like upper (:localHome) "
								+ "and dataLancamento = (:dataLanc)",
						LancamentoSaqueDespesa.class);
		query.setParameter("localHome",  "%" + usuarioLocal.getLocalUsuario() + "%");
		query.setParameter("dataLanc", dtLancamento);

		List<LancamentoSaqueDespesa> resultado = query.getResultList();

		try {
			Writer = new BufferedWriter(new FileWriter("C:\\Temp\\fechCaixinha_"+usuarioLocal.getSaldoAtual().getNomeUnidade()+"_"+fmtString.format(dtLancamento)+".txt"));
			int sequencia = 0;
			for (LancamentoSaqueDespesa valores : resultado) {
				
				String dataLancamento = fmtString.format(valores.getDataLancamento());
				String dataUltimodiaMes = fmtString.format(buscaUltimoDiaMes());
				
				if(valores.getUsuarioLancamento().equals("ADMIN")){
					this.naturezaContabil = valores.getTipoLancamento().toString().replace("SAQUE", "D").replace("DESPESA", "C");
				}else{
					this.naturezaContabil = valores.getTipoLancamento().toString().replace("SAQUE", "C").replace("DESPESA", "D");
				}

				sequencia = sequencia + 1;
				
				if(valores.getNomeFornecedor() == null){					
					this.descHistoPadrao = valores.getHistoricoPadrao().getUtilizacao().toString();
					this.numNfCupom = " ";
				}else{
					this.descHistoPadrao = valores.getNomeFornecedor().toString();
					this.numNfCupom = valores.getNumeroNfCupon();
				}
					

				Writer.write("36"+ padRight(valores.getCentroCusto().getUnidadeNegocio().getEmpresa().getCodigo(), 2)+ "  "
						+ "" + padLeft(dataLancamento,8) + ""
						+ "" + padLeft(valores.getId().toString(), 6).replace(' ', '0') + ""
						+ "" + padLeft(Integer.toString(sequencia), 3).replace(' ', '0') + ""
						+ "" + padLeft(naturezaContabil.toString(), 1) + ""
						+ "" + padRight(valores.getHistoricoPadrao().getCtaCtbDeb().getContaContabilExp().toString(), 8).replace(' ', '0') + "     "
						+ "" + padRight(valores.getHistoricoPadrao().getCtaCtbCred().getContaContabilExp().toString(), 8).replace(' ', '0') + "     "
						+ "" + padRight(valores.getCentroCusto().getCodigoGc().toString(), 5).replace(' ', '0') +""
						+ "" + padLeft(valores.getValorLancamento().toString().replace(".", ""),12).replace(' ', '0') + ""
						+ "" + padLeft(naturezaContabil.replace("C",valores.getHistoricoPadrao().getCodHistCred().toString()).replace("D", valores.getHistoricoPadrao().getCodHistDeb().toString()),3).replace(' ', '0') + ""
						+ "" + "0" //verificar seq 15 do layout 36...
						+ "" + padRight(this.descHistoPadrao,50) + "00000000000000" + "      "
						+ "" + "1" + "" //CNPJ = 1 CPF = 2 verificar...
						+ "" + padLeft(naturezaContabil.replace("C", valores.getHistoricoPadrao().getCtaCtbCred().getContaContabilRed().toString()).replace("D", valores.getHistoricoPadrao().getCtaCtbDeb().getContaContabilRed().toString()),4).replace(' ', '0') + ""
						+ "" + "2" + ""
						+ "" + padLeft(dataLancamento,8) + ""
						+ "" + padLeft(dataUltimodiaMes,8) + "00000000" + ""
						+ "" + "S" + "                    "
						+ "" + padLeft(valores.getCentroCusto().getUnidadeNegocio().getCodigo().toString(), 3).replace(' ', '0') + ""
						+ "" + padLeft(numNfCupom, 9).replace(' ', '0'));
				Writer.newLine();

			}
		} catch (IOException e) {
			System.out.println("Não foi possível gravar o arquivo "
					+ e.getMessage());
		} finally {
			try {
				Writer.close();
			} catch (IOException e) {

			}
		}
	}

	public static String padRight(String s, int n) {
		return String.format("%1$-" + n + "s", s);
	}

	public static String padLeft(String s, int n) {
		return String.format("%1$" + n + "s", s);
	}
	
	public Date buscaUltimoDiaMes(){
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime( new Date() );
		         
		int dia = cal.getActualMaximum( Calendar.DAY_OF_MONTH );
		int mes = (cal.get(Calendar.MONDAY)+1);
		int ano = cal.get(Calendar.YEAR);
		         		         
		try {
		    Date data = (new SimpleDateFormat("dd/MM/yyyy")).parse( dia+"/"+mes+"/"+ano );
		    return data;
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		return null;		
	}
	
	public Object[] buscaTotaisFechamentoCx(Date dataFechamento) throws NegocioException{
		TypedQuery<Object[]> query = manager.createQuery(
				"select dataLancamento, "
				+ "local, "
				+ "sum(case when tipoLancamento = 'SAQUE' then valorLancamento else 0.00 end) as SAQUE, "
				+ "sum(case when tipoLancamento = 'DESPESA' then valorLancamento else 0.00 end) as DESPESA "
				+ "from LancamentoSaqueDespesa "
				+ "where local like upper (:localHome) "
				+ "and dataLancamento = (:dataLanc) "
				+ "group by dataLancamento, "
				+ "local", Object[].class);
		query.setParameter("localHome","%" + usuarioLocal.getLocalUsuario() + "%");
		query.setParameter("dataLanc", dataFechamento);
		
		return query.getSingleResult();		
		
		}

	public void adicionar(LancamentoSaqueDespesa lancamentoSaqueDespesa){
		this.manager.persist(lancamentoSaqueDespesa);
	}
	
	
	public LancamentoSaqueDespesa guardar (LancamentoSaqueDespesa lancamentoSaqueDespesa){
		return this.manager.merge(lancamentoSaqueDespesa);
	}
	
	public void remover(LancamentoSaqueDespesa lancamentoSaqueDespesa){
		this.manager.remove(lancamentoSaqueDespesa);
	}
	
	
	

}

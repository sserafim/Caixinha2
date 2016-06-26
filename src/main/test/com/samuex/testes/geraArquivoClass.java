package com.samuex.testes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.samuex.financeiro.model.LancamentoSaqueDespesa;
import com.samuex.financeiro.repository.LancamentosSaquesDespesas;
import com.samuex.financeiro.service.CadastroLacamentoSaqueDespesa;
import com.samuex.financeiro.service.NegocioException;
import com.samuex.financeiro.util.EntityManagerProducer;

@RunWith(MockitoJUnitRunner.class)
public class geraArquivoClass {

	@InjectMocks
	private EntityManagerProducer manager;
	private int sequencia = 0;
	private String descHistoPadrao;
	private String numNfCupom;
	BufferedWriter Writer = null;
	
	@InjectMocks
	private CadastroLacamentoSaqueDespesa cadastroTransactional;
	

	@InjectMocks
	private LancamentosSaquesDespesas lancamentoSaquesDespesasDAO;

	@Test
	public void buscaLancamentoHome() throws ParseException {
		
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy"); 
		SimpleDateFormat fmtString = new SimpleDateFormat("ddMMyyyy");
		Date dtLancamento = fmt.parse("22/06/2016");

		TypedQuery<LancamentoSaqueDespesa> query = manager
				.createEntityManager().createQuery(
						"from LancamentoSaqueDespesa "
								+ "where local like upper (:localHome) "
								+ "and dataLancamento = (:dataLanc)",
						LancamentoSaqueDespesa.class);
		query.setParameter("localHome", "%" + "LEOPOLDINA" + "%");
		query.setParameter("dataLanc", dtLancamento);

		List<LancamentoSaqueDespesa> resultado = query.getResultList();
		
		try {
			Writer = new BufferedWriter(new FileWriter("C:\\Temp\\fechCaixinha_"+fmtString.format(dtLancamento)+".txt"));

			for (LancamentoSaqueDespesa valores : resultado) {
				
				
				
				String dataLancamento = fmtString.format(valores.getDataLancamento());
				String dataUltimodiaMes = fmtString.format(buscaUltimoDiaMes());
				String naturezaContabil = valores.getTipoLancamento().toString().replace("SAQUE", "C").replace("DESPESA", "D");
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

	
	public void atualizaStatusIntegracao(Long idLanc){
		LancamentoSaqueDespesa lancSaqueDespesa = this.lancamentoSaquesDespesasDAO.porId(idLanc);
		lancSaqueDespesa.setStatusIntegracao("S");
		try {
			this.cadastroTransactional.salvar(lancSaqueDespesa);

		} catch (NegocioException e) {
			e.printStackTrace();
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
	
}

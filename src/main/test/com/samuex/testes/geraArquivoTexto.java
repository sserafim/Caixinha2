package com.samuex.testes;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.samuex.financeiro.util.EntityManagerProducer;



@RunWith(MockitoJUnitRunner.class)
public class geraArquivoTexto{

	
	@InjectMocks
	private EntityManagerProducer manager;

	BufferedWriter Writer = null;

	
	
	@Test
	public void buscaLancamentoHome() throws ParseException{
		
		 SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy"); //Formato de data    
		 Date dtLancamento = fmt.parse("22/06/2016"); 
		 
		
	//	 String str = fmt.format(dtLancamento);  //Converte data para String
			
		
		TypedQuery<Object[]> query = manager.createEntityManager().createQuery(
				"select dataLancamento, "
				+ "(case when tipoLancamento = 'SAQUE' then valorLancamento else 0.00 end) as SAQUE, "
				+ "(case when tipoLancamento = 'DESPESA' then valorLancamento else 0.00 end) as DESPESA "
				+ "from LancamentoSaqueDespesa "
				+ "where local like upper (:localHome) "
				+ "and dataLancamento = (:dataLanc)", Object[].class);
		query.setParameter("localHome", "%" + "LEOPOLDINA" + "%");	
		query.setParameter("dataLanc", dtLancamento);
		
		List<Object[]> resultado = query.getResultList();
		
		try{
			Writer = new BufferedWriter(new FileWriter("C:\\Temp\\testSos.txt"));
			
			for(Object[] valores : resultado){

				Writer.write("34  " + valores[0] + ";"
								+ "" + valores[1] + ";"
								+ "" + valores[2]);
				Writer.newLine();

			}	
		}catch(IOException e){
			System.out.println("Não foi possível gravar o arquivo " + e.getMessage());				
			}finally{
				try{
					Writer.close();
				}catch(IOException e){
					
				}
			}
			
	}
}


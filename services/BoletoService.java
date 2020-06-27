package br.com.vini.projetointegrador.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.vini.projetointegrador.dominio.PagamentoComBoleto;

@Service
public class BoletoService {
	
	public void PreencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH,7);
		pagto.setDataVencimento(cal.getTime());
	}
}

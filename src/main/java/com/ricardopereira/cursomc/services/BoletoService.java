package com.ricardopereira.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import com.ricardopereira.cursomc.domain.PagamentoComBoleto;

public class BoletoService {
	
	public void preencherPagamentoComBoleto(PagamentoComBoleto pgto, Date instantePedido) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(instantePedido);
		cal.add(Calendar.DAY_OF_WEEK, 7);
		pgto.setDataPagamento(cal.getTime());
	}

}

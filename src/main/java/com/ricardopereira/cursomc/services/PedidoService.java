package com.ricardopereira.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ricardopereira.cursomc.domain.ItemPedido;
import com.ricardopereira.cursomc.domain.PagamentoComBoleto;
import com.ricardopereira.cursomc.domain.Pedido;
import com.ricardopereira.cursomc.domain.enums.EstadoPagamento;
import com.ricardopereira.cursomc.repositories.ItemPedidoRepository;
import com.ricardopereira.cursomc.repositories.PagamentoRepository;
import com.ricardopereira.cursomc.repositories.PedidoRepository;
import com.ricardopereira.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido find(Integer id) {	
		Optional <Pedido> obj = repo.findById(id);
		
	return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrato id:" + id +", Tipo: "+ Pedido.class.getName()));	
		
		
	}
	
	
	@Transactional
	public Pedido insert(Pedido obj) {
		
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreço());
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}

}

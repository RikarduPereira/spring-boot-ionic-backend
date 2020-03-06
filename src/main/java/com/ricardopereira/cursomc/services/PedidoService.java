package com.ricardopereira.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricardopereira.cursomc.domain.Pedido;
import com.ricardopereira.cursomc.repositories.PedidoRepository;
import com.ricardopereira.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {	
		Optional <Pedido> obj = repo.findById(id);
		
	return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrato id:" + id +", Tipo: "+ Pedido.class.getName()));
		
		
		
		
	}

}

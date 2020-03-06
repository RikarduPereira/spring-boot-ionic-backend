package com.ricardopereira.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricardopereira.cursomc.domain.Categoria;
import com.ricardopereira.cursomc.repositories.CategoriaRepository;
import com.ricardopereira.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {	
		Optional <Categoria> obj = repo.findById(id);
		
	return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrato id:" + id +", Tipo: "+ Categoria.class.getName()));	
		
		
	}
	
	public Categoria insert (Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

}

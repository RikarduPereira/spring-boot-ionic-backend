package com.ricardopereira.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ricardopereira.cursomc.domain.Cliente;
import com.ricardopereira.cursomc.dto.ClienteNewDTO;
import com.ricardopereira.cursomc.repositories.ClienteRepository;
import com.ricardopereira.cursomc.services.exceptions.FieldMessage;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		
		
		List<FieldMessage> list = new ArrayList<>();
		

		Cliente aux = repo.findByEmail(objDto.getEmail());
		
		if (aux != null) {
			
			list.add(new FieldMessage("email","Email já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
